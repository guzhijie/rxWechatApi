package com.kisen.mms.wx.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.kisen.mms.wx.HttpClient;
import com.kisen.mms.wx.api.account.AccountManagement;
import com.kisen.mms.wx.api.account.ActionInfo;
import com.kisen.mms.wx.api.account.QRReq;
import com.kisen.mms.wx.api.account.QRRet;
import com.kisen.mms.wx.api.intelligent.AI;
import com.kisen.mms.wx.api.intelligent.ORC;
import com.kisen.mms.wx.api.media.MediaManagement;
import com.kisen.mms.wx.api.media.MediaType;
import com.kisen.mms.wx.api.menu.MenuManagement;
import com.kisen.mms.wx.api.menu.WXButton;
import com.kisen.mms.wx.api.msg.*;
import com.kisen.mms.wx.api.user.UserInfoQuery;
import com.kisen.mms.wx.api.user.UserListRet;
import com.kisen.mms.wx.api.user.UserManagement;
import com.kisen.mms.wx.api.webpage.WebpageManagement;
import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.log4j.Logger;
import org.reactivestreams.Publisher;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public final class WXServerApiWrapper {
    private static final Logger logger = Logger.getLogger(WXServerApiWrapper.class);
    private final UserManagement m_userManagement;
    private final MessageManagement m_messageManagement;
    private final MenuManagement m_menuManagement;
    private final AccountManagement m_accountManagement;
    private final MediaManagement m_mediaManagement;
    private final WebpageManagement m_webpageManagement;
    private final AI m_ai;
    private final ORC m_orc;
    private final WXServerApi m_wxServerApi;

    private final String AppID;
    private final String AppSecret;
    private final Object m_gettingTokenLock = new Object();
    //    private final PublishSubject<String> m_tokenEventBus = PublishSubject.create();
    private String m_accessToken;
//    private boolean m_isGettingToken = false;

    public WXServerApiWrapper(String appID, String appSecret) {
        this.AppID = appID;
        this.AppSecret = appSecret;
        HttpClient httpClient = HttpClient.getInstance(WXServerApi.WX_API_BASE_URL);
        this.m_userManagement = httpClient.createApi(UserManagement.class);
        this.m_messageManagement = httpClient.createApi(MessageManagement.class);
        this.m_menuManagement = httpClient.createApi(MenuManagement.class);
        this.m_accountManagement = httpClient.createApi(AccountManagement.class);
        this.m_mediaManagement = httpClient.createApi(MediaManagement.class);
        this.m_ai = httpClient.createApi(AI.class);
        this.m_orc = httpClient.createApi(ORC.class);
        this.m_webpageManagement = httpClient.createApi(WebpageManagement.class);
        this.m_wxServerApi = httpClient.createApi(WXServerApi.class);
    }

    public static ObservableTransformer<ResponseBody, FileDownloadInfo> fileDownloadTransformer() {
        return new ObservableTransformer<ResponseBody, FileDownloadInfo>() {
            @Override
            public ObservableSource<FileDownloadInfo> apply(Observable<ResponseBody> observable) {
                return observable.concatMap(new Function<ResponseBody, ObservableSource<FileDownloadInfo>>() {
                    @Override
                    public ObservableSource<FileDownloadInfo> apply(ResponseBody responseBody) throws Exception {
                        final long total = responseBody.contentLength();
                        final InputStream inputStream = responseBody.byteStream();
                        final byte[] bytes = new byte[1024];
                        return Observable.create(new ObservableOnSubscribe<FileDownloadInfo>() {
                            @Override
                            public void subscribe(ObservableEmitter<FileDownloadInfo> emitter) throws Exception {
                                for (; ; ) {
                                    int n = inputStream.read(bytes);
                                    if (n == -1) {
                                        emitter.onComplete();
                                        break;
                                    } else {
                                        emitter.onNext(new FileDownloadInfo(n, total, bytes));
                                    }
                                }
                            }
                        });
                    }
                });
            }
        };
    }

    public <D> SingleTransformer<JSONObject, D> toJavaObject(@NonNull final Class<D> clazz) {
        return new SingleTransformer<JSONObject, D>() {
            @Override
            public SingleSource<D> apply(Single<JSONObject> upstream) {
                return upstream.subscribeOn(Schedulers.newThread())
                        .map(new Function<JSONObject, D>() {
                            @Override
                            public D apply(JSONObject jsonObject) throws Exception {
                                logger.debug(jsonObject.toJSONString());
                                Integer errcode = jsonObject.getInteger("errcode");
                                if (null != errcode && 0 != errcode) {
                                    switch (errcode) {
                                        case ErrCode.ACCESS_TOKEN_ILLEGAL:
                                            throw new TokenException(ErrCode.ACCESS_TOKEN_ILLEGAL);
                                        case ErrCode.ACCESS_TOKEN_TIMEOUT:
                                            throw new TokenException(ErrCode.ACCESS_TOKEN_TIMEOUT);
                                        default:
                                            throw new WXResultException(jsonObject);
                                    }
                                } else {
                                    //noinspection unchecked
                                    return JSONObject.class.equals(clazz) ? (D) jsonObject : JSONObject.toJavaObject(jsonObject, clazz);
                                }
                            }
                        })
                        .retryWhen(new Function<Flowable<Throwable>, Publisher<?>>() {
                            @Override
                            public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
                                return throwableFlowable.concatMap(new Function<Throwable, Publisher<?>>() {
                                    @Override
                                    public Publisher<?> apply(Throwable throwable) throws Exception {
                                        if (throwable instanceof TokenException) {
                                            TokenException tokenException = (TokenException) throwable;
                                            if (tokenException.getErrcode() == ErrCode.ACCESS_TOKEN_ILLEGAL
                                                    || tokenException.getErrcode() == ErrCode.ACCESS_TOKEN_TIMEOUT) {
                                                synchronized (m_gettingTokenLock) {
                                                    if (m_accessToken == null) {
                                                        return getAccessToken().toFlowable();
                                                    } else {
                                                        return Flowable.just(m_accessToken);
                                                    }
                                                }
                                            } else {
                                                return Flowable.<AccessTokenRet>error(throwable);
                                            }
                                        } else {
                                            return Flowable.<AccessTokenRet>error(throwable);
                                        }
                                    }
                                });
                            }
                        })
                        .observeOn(Schedulers.trampoline());
            }
        };
    }

    public <D> SingleTransformer<JSONObject, List<D>> toJavaList(@NonNull final Class<D> clazz) {
        return new SingleTransformer<JSONObject, List<D>>() {
            @Override
            public SingleSource<List<D>> apply(Single<JSONObject> upstream) {
                return upstream.compose(WXServerApiWrapper.this.toJavaObject(JSONArray.class))
                        .map(new Function<JSONArray, List<D>>() {
                            @Override
                            public List<D> apply(JSONArray jsonArray) throws Exception {
                                return jsonArray.toJavaList(clazz);
                            }
                        });
            }
        };
    }

    public Single<List<WXButton>> getMenuTree() {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String s) throws Exception {
                        return m_menuManagement.get_current_selfmenu_info(s);
                    }
                })
                .compose(toJavaObject(JSONObject.class))
                .map(new Function<JSONObject, List<WXButton>>() {
                    @Override
                    public List<WXButton> apply(JSONObject jsonObject) throws Exception {
                        JSONArray jsonArray = jsonObject.getJSONObject("selfmenu_info").getJSONArray("button");
                        return JSONObject.parseObject(JSONArray.toJSONString(jsonArray), new TypeReference<List<WXButton>>() {
                        });
                    }
                });
    }

    public Single<WXResult> updateMenuTree(List<WXButton> wxButtonList) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String s) throws Exception {
                        return m_menuManagement.create_menu(s, Collections.singletonMap("button", wxButtonList));
                    }
                })
                .compose(toJavaObject(WXResult.class));
    }

    public Single<JSONObject> addTemplate(final String templateCode) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_messageManagement.add_template(token, Collections.singletonMap("template_id_short", templateCode));
                    }
                })
                .compose(toJavaObject(JSONObject.class));
    }

    public Single<JSONObject> sendTemplateMessage(final TemplateMessage templateMessage) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_messageManagement.send_template_message(token, templateMessage);
                    }
                })
                .compose(toJavaObject(JSONObject.class));
    }

    public Single<List<PrivateTemplateRet>> getAllPrivateTemplate() {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_messageManagement.get_all_private_template(token);
                    }
                })
                .compose(toJavaObject(JSONObject.class))
                .map(new Function<JSONObject, List<PrivateTemplateRet>>() {
                    @Override
                    public List<PrivateTemplateRet> apply(JSONObject jsonObject) throws Exception {
                        return jsonObject.getJSONArray("template_list").toJavaList(PrivateTemplateRet.class);
                    }
                });
    }

    public Single<UserListRet> getUsers(final String next_openid) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_userManagement.get_users(token, next_openid);
                    }
                })
                .compose(toJavaObject(UserListRet.class));
    }

    public Single<MessageRet> sendMessage(List<String> openids, String content) {
        final TextMessage textMessage = new TextMessage()
                .setTouser(openids)
                .setText(new TextMessage.TextInfo().setContent(content));
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_messageManagement.send_message(token, textMessage);
                    }
                })
                .compose(toJavaObject(MessageRet.class));
    }

    public Single<WXResult> deleteMessage(Long msg_id, Long article_idx) {
        final Map<String, Long> messageParma = new HashMap<>();
        messageParma.put("msg_id", msg_id);
        messageParma.put("article_idx", article_idx);
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_messageManagement.delete_message(token, messageParma);
                    }
                })
                .compose(toJavaObject(WXResult.class));
    }

    public Single<JSONArray> batchGetUserInfo(final List<UserInfoQuery> userInfoQueries) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_userManagement.batch_get_user_info(token, Collections.singletonMap("user_list", userInfoQueries));
                    }
                })
                .compose(toJavaObject(JSONObject.class))
                .map(new Function<JSONObject, JSONArray>() {
                    @Override
                    public JSONArray apply(JSONObject jsonObject) throws Exception {
                        return jsonObject.getJSONArray("user_info_list");
                    }
                });
    }

    public Single<String> getAccessToken() {
        return m_wxServerApi.get_access_token(AppID, AppSecret)
                .subscribeOn(Schedulers.newThread())
                .map(new Function<JSONObject, String>() {
                    @Override
                    public String apply(JSONObject jsonObject) throws Exception {
                        AccessTokenRet accessTokenRet = JSONObject.toJavaObject(jsonObject, AccessTokenRet.class);
                        m_accessToken = accessTokenRet.getAccess_token();
                        int expires_in = accessTokenRet.getExpires_in();
                        logger.info("getAccessToken : " + m_accessToken);
                        return m_accessToken;
                    }
                })
                .observeOn(Schedulers.trampoline());
    }

    public Single<QRRet> createQrCode(String scene_str) {
        final QRReq qrReq = new QRReq().setAction_name("QR_STR_SCENE")
                .setAction_info(new ActionInfo(scene_str));
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_accountManagement.create_qrcode(token, qrReq);
                    }
                })
                .compose(toJavaObject(QRRet.class));
    }

    public Single<QRRet> createQrCode(String scene_str, Long expire_seconds) {
        final QRReq qrReq = new QRReq().setAction_name("QR_STR_SCENE")
                .setExpire_seconds(expire_seconds)
                .setAction_info(new ActionInfo(scene_str));
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_accountManagement.create_qrcode(token, qrReq);
                    }
                })
                .compose(toJavaObject(QRRet.class));
    }

    public Single<QRRet> createQrCode(Long scene_id) {
        final QRReq qrReq = new QRReq().setAction_name("QR_SCENE")
                .setAction_info(new ActionInfo(scene_id));
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_accountManagement.create_qrcode(token, qrReq);
                    }
                })
                .compose(toJavaObject(QRRet.class));
    }

    public Single<QRRet> createQrCode(Long scene_id, Long expire_seconds) {
        final QRReq qrReq = new QRReq().setAction_name("QR_SCENE")
                .setExpire_seconds(expire_seconds)
                .setAction_info(new ActionInfo(scene_id));
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_accountManagement.create_qrcode(token, qrReq);
                    }
                })
                .compose(toJavaObject(QRRet.class));
    }

    public Observable<FileDownloadInfo> showQrCode(final String ticket) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<ResponseBody>>() {
                    @Override
                    public SingleSource<ResponseBody> apply(String token) throws Exception {
                        return m_accountManagement.showqrcode(ticket);
                    }
                })
                .toObservable()
                .compose(fileDownloadTransformer());
    }

    public Single<JSONObject> uploadMedia(final MediaType mediaType, File file) {
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
        final MultipartBody.Part part = MultipartBody.Part.createFormData(mediaType.toString(), file.getName(), requestBody);
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_mediaManagement.upload_media(token, mediaType.toString(), part);
                    }
                })
                .compose(toJavaObject(JSONObject.class));
    }

    public Observable<FileDownloadInfo> downloadMedia(final String media_id) {
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<ResponseBody>>() {
                    @Override
                    public SingleSource<ResponseBody> apply(String token) throws Exception {
                        return m_mediaManagement.get_media(token, media_id);
                    }
                })
                .toObservable()
                .compose(fileDownloadTransformer());
    }

    public Single<JSONObject> idcard(File file) {
        RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
        final MultipartBody.Part part = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
        return checkAccessToken()
                .flatMap(new Function<String, SingleSource<JSONObject>>() {
                    @Override
                    public SingleSource<JSONObject> apply(String token) throws Exception {
                        return m_orc.idcard(token, part);
                    }
                })
                .compose(toJavaObject(JSONObject.class));
    }

    public Single<JSONObject> oauth2(String code) {
        return this.m_webpageManagement.oauth2(this.AppID, this.AppSecret, code, "authorization_code");
    }

    public Single<ResponseBody> authorize(String url, String STATE) {
        return this.m_webpageManagement.authorize(AppID, url, "code", "SCOPE", STATE);
    }

    private Single<String> checkAccessToken() {
        return Single.create(new SingleOnSubscribe<String>() {
            @Override
            public void subscribe(final SingleEmitter<String> singleEmitter) throws Exception {
                if (null == m_accessToken) {
                    try {
                        synchronized (m_gettingTokenLock) {
                            String token = blockingGetAccessToken();
                            singleEmitter.onSuccess(token);
                        }
                    } catch (Exception e) {
                        singleEmitter.onError(new TokenException(ErrCode.ACCESS_TOKEN_GET_ERR));
                    }
                } else {
                    singleEmitter.onSuccess(m_accessToken);
                }
            }
        }).retry(throwable -> throwable instanceof TokenException && ((TokenException) throwable).getErrcode() == ErrCode.ACCESS_TOKEN_GET_ERR);
    }

    private synchronized String blockingGetAccessToken() {
        return getAccessToken().blockingGet();
    }
}
