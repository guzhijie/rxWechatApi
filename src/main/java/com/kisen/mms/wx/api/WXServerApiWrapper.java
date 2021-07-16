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
import com.kisen.mms.wx.api.kf.KFAccount;
import com.kisen.mms.wx.api.kf.KFAccountManagement;
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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.log4j.Logger;

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
  private final KFAccountManagement m_kfAccountManagement;
  private final WXServerApi m_wxServerApi;
  private final String AppID;
  private final String AppSecret;
  private final Object lock = new Object();
  private volatile String m_accessToken;
  private volatile Long m_lastTokenGetTime;

  public WXServerApiWrapper(String appID, String appSecret) {
    AppID = appID;
    AppSecret = appSecret;
    HttpClient httpClient = HttpClient.getInstance(WXServerApi.WX_API_BASE_URL);
    m_userManagement = httpClient.createApi(UserManagement.class);
    m_messageManagement = httpClient.createApi(MessageManagement.class);
    m_menuManagement = httpClient.createApi(MenuManagement.class);
    m_accountManagement = httpClient.createApi(AccountManagement.class);
    m_mediaManagement = httpClient.createApi(MediaManagement.class);
    m_ai = httpClient.createApi(AI.class);
    m_orc = httpClient.createApi(ORC.class);
    m_webpageManagement = httpClient.createApi(WebpageManagement.class);
    m_kfAccountManagement = httpClient.createApi(KFAccountManagement.class);
    m_wxServerApi = httpClient.createApi(WXServerApi.class);
  }

  /*****************************************************************************************************************/

  public Single<Boolean> updateremark(String openId, String remark) {
    Map<String, String> map = new HashMap<>(2);
    map.put("openId", openId);
    map.put("remark", remark);
    return checkAccessToken(token -> m_userManagement.updateremark(token, map), JSONObject.class)
        .map(jsonObject -> "ok".equals(jsonObject.get("errmsg")));
  }

  public Single<JSONObject> getUserInfo(String openId) {
    return checkAccessToken(
        token -> m_userManagement.get_user_info(token, openId, "zh_CN"), JSONObject.class);
  }

  public Single<JSONObject> getKfList() {
    return checkAccessToken(m_kfAccountManagement::getkflist, JSONObject.class);
  }

  public Single<Boolean> addKfAccount(KFAccount kfAccount) {
    return checkAccessToken(
            s -> m_kfAccountManagement.add_kfaccount(s, kfAccount), JSONObject.class)
        .map(jsonObject -> "ok".equals(jsonObject.getString("errmsg")));
  }

  public Single<Boolean> delKfAccount(KFAccount kfAccount) {
    return checkAccessToken(
            s -> m_kfAccountManagement.del_kfaccount(s, kfAccount), JSONObject.class)
        .map(jsonObject -> "ok".equals(jsonObject.getString("errmsg")));
  }

  /**
   * 获得当前微信公众号的菜单
   *
   * @return
   */
  public Single<List<WXButton>> getMenuTree() {
    return checkAccessToken(m_menuManagement::get_current_selfmenu_info, JSONObject.class)
        .map(
            jsonObject -> {
              JSONArray jsonArray =
                  jsonObject.getJSONObject("selfmenu_info").getJSONArray("button");
              return JSONObject.parseObject(
                  JSONArray.toJSONString(jsonArray), new TypeReference<List<WXButton>>() {});
            });
  }

  /**
   * 更新微信公众号菜单
   *
   * @param wxButtonList
   * @return
   */
  public Single<WXResult> updateMenuTree(List<WXButton> wxButtonList) {
    return checkAccessToken(
        s -> m_menuManagement.create_menu(s, Collections.singletonMap("button", wxButtonList)),
        WXResult.class);
  }

  public Single<JSONObject> addTemplate(String templateCode) {
    return checkAccessToken(
        s ->
            m_messageManagement.add_template(
                s, Collections.singletonMap("template_id_short", templateCode)),
        JSONObject.class);
  }

  /**
   * 发送模板消息
   *
   * @param templateMessage
   * @return
   */
  public Single<JSONObject> sendTemplateMessage(TemplateMessage templateMessage) {
    return checkAccessToken(
        s -> m_messageManagement.send_template_message(s, templateMessage), JSONObject.class);
  }

  /**
   * 获取当前微信公众号配置的模板消息列表
   *
   * @return
   */
  public Single<List<PrivateTemplateRet>> getAllPrivateTemplate() {
    return checkAccessToken(m_messageManagement::get_all_private_template, JSONObject.class)
        .map(
            jsonObject ->
                jsonObject.getJSONArray("template_list").toJavaList(PrivateTemplateRet.class));
  }

  /**
   * 获取关注当前公众号的用户
   *
   * @param next_openid
   * @return
   */
  public Single<UserListRet> getUsers(String next_openid) {
    return checkAccessToken(s -> m_userManagement.get_users(s, next_openid), UserListRet.class);
  }

  /**
   * 发送普通消息给指定的用户
   *
   * @param openids
   * @param content
   * @return
   */
  public Single<MessageRet> sendMessage(List<String> openids, String content) {
    TextMessage textMessage = new TextMessage();
    textMessage.setTouser(openids);
    TextMessage.TextInfo textInfo = new TextMessage.TextInfo();
    textInfo.setContent(content);
    textMessage.setText(textInfo);
    return checkAccessToken(
        s -> m_messageManagement.send_message(s, textMessage), MessageRet.class);
  }

  /**
   * 删除服务器端的消息
   *
   * @param msg_id
   * @param article_idx
   * @return
   */
  public Single<WXResult> deleteMessage(Long msg_id, Long article_idx) {
    Map<String, Long> messageParma = new HashMap<>();
    messageParma.put("msg_id", msg_id);
    messageParma.put("article_idx", article_idx);
    return checkAccessToken(
        s -> m_messageManagement.delete_message(s, messageParma), WXResult.class);
  }

  /**
   * 批量获得关注公众号用户信息
   *
   * @param userInfoQueries
   * @return
   */
  public Single<JSONArray> batchGetUserInfo(List<UserInfoQuery> userInfoQueries) {
    return checkAccessToken(
            s ->
                m_userManagement.batch_get_user_info(
                    s, Collections.singletonMap("user_list", userInfoQueries)),
            JSONObject.class)
        .map(jsonObject -> jsonObject.getJSONArray("user_info_list"));
  }

  /**
   * 获得token
   *
   * @return
   */
  public Single<String> getAccessToken() {
    return m_wxServerApi
        .get_access_token(AppID, AppSecret)
        .subscribeOn(Schedulers.newThread())
        .map(
            jsonObject -> {
              int errcode = jsonObject.getIntValue("errcode");
              if (errcode == 0) {
                AccessTokenRet accessTokenRet =
                    JSONObject.toJavaObject(jsonObject, AccessTokenRet.class);
                m_accessToken = accessTokenRet.getAccess_token();
                m_lastTokenGetTime = System.currentTimeMillis();
                int expires_in = accessTokenRet.getExpires_in();
                logger.info("getAccessToken : " + m_accessToken);
                return m_accessToken;
              } else if (errcode == ErrCode.IP_NOT_IN_WHITE_LIST) {
                throw new WXResultException(jsonObject);
              } else {
                throw new WXResultException(jsonObject);
              }
            })
        .observeOn(Schedulers.trampoline());
  }

  /**
   * 创建二维码
   *
   * @param scene_str
   * @return
   */
  public Single<QRRet> createQrCode(String scene_str) {
    QRReq qrReq =
        QRReq.builder().action_name("QR_STR_SCENE").action_info(new ActionInfo(scene_str)).build();
    return checkAccessToken(s -> m_accountManagement.create_qrcode(s, qrReq), QRRet.class);
  }

  public Single<QRRet> createQrCode(String scene_str, Long expire_seconds) {
    QRReq qrReq =
        QRReq.builder()
            .action_name("QR_STR_SCENE")
            .action_info(new ActionInfo(scene_str))
            .expire_seconds(expire_seconds)
            .build();
    return checkAccessToken(s -> m_accountManagement.create_qrcode(s, qrReq), QRRet.class);
  }

  public Single<QRRet> createQrCode(Long scene_id) {
    QRReq qrReq =
        QRReq.builder().action_name("QR_SCENE").action_info(new ActionInfo(scene_id)).build();
    return checkAccessToken(s -> m_accountManagement.create_qrcode(s, qrReq), QRRet.class);
  }

  public Single<QRRet> createQrCode(Long scene_id, Long expire_seconds) {
    QRReq qrReq =
        QRReq.builder()
            .action_name("QR_SCENE")
            .expire_seconds(expire_seconds)
            .action_info(new ActionInfo(scene_id))
            .build();
    return checkAccessToken(s -> m_accountManagement.create_qrcode(s, qrReq), QRRet.class);
  }

  /**
   * 获取相关的二维码
   *
   * @param ticket
   * @return
   */
  public Observable<FileDownloadInfo> showQrCode(String ticket) {
    return checkAccessToken(s -> m_accountManagement.showqrcode(ticket));
  }

  /**
   * 上传多媒体
   *
   * @param mediaType
   * @param file
   * @return
   * @see #downloadMedia(String)
   */
  public Single<JSONObject> uploadMedia(MediaType mediaType, File file) {
    RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
    MultipartBody.Part part =
        MultipartBody.Part.createFormData(mediaType.toString(), file.getName(), requestBody);
    return checkAccessToken(
        s -> m_mediaManagement.upload_media(s, mediaType.toString(), part), JSONObject.class);
  }

  /**
   * 下载上传过的多媒体
   *
   * @param media_id
   * @return
   * @see #uploadMedia(MediaType, File)
   */
  public Observable<FileDownloadInfo> downloadMedia(String media_id) {
    return checkAccessToken(s -> m_mediaManagement.get_media(s, media_id));
  }

  /**
   * 身份证识别
   *
   * @param file
   * @return
   */
  public Single<JSONObject> idcard(File file) {
    RequestBody requestBody = RequestBody.create(MultipartBody.FORM, file);
    MultipartBody.Part part = MultipartBody.Part.createFormData("img", file.getName(), requestBody);
    return checkAccessToken(s -> m_orc.idcard(s, part), JSONObject.class);
  }

  /**
   * 微信网页授权，通过code获取当前用户的openid
   *
   * @param code
   * @return
   * @see #authorize(String, String)
   */
  public Single<JSONObject> oauth2(String code) {
    return m_webpageManagement.oauth2(AppID, AppSecret, code, "authorization_code");
  }

  /**
   * 微信网页授权接口
   *
   * @param url
   * @param STATE
   * @return
   * @see #oauth2(String)
   */
  public Single<ResponseBody> authorize(String url, String STATE) {
    return m_webpageManagement.authorize(AppID, url, "code", "SCOPE", STATE);
  }

  /*****************************************************************************************************************/
  private <D> SingleTransformer<JSONObject, D> toJavaObject(@NonNull Class<D> clazz) {
    return upstream ->
        upstream
            .subscribeOn(Schedulers.newThread())
            .map(
                jsonObject -> {
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
                    return JSONObject.toJavaObject(jsonObject, clazz);
                  }
                })
            .retryWhen(
                throwableFlowable ->
                    throwableFlowable.concatMap(
                        throwable -> {
                          if (throwable instanceof TokenException) {
                            TokenException tokenException = (TokenException) throwable;
                            int errcode = tokenException.getErrcode();
                            if (ErrCode.ACCESS_TOKEN_ILLEGAL == errcode
                                || ErrCode.ACCESS_TOKEN_TIMEOUT == errcode) {
                              try {
                                synchronized (lock) {
                                  long now = System.currentTimeMillis();
                                  /*如果未曾获得token，或者已经超时*/
                                  if (m_lastTokenGetTime == null
                                      || (now - m_lastTokenGetTime) / 1000 > 7200) {
                                    String token = getAccessToken().retry(3).blockingGet();
                                    return Flowable.just(token);
                                  } else {
                                    return Flowable.just(m_accessToken);
                                  }
                                }
                              } catch (RuntimeException e) {
                                return Flowable.error(e);
                              }
                            } else {
                              return Flowable.<AccessTokenRet>error(throwable);
                            }
                          } else {
                            return Flowable.<AccessTokenRet>error(throwable);
                          }
                        }))
            .observeOn(Schedulers.trampoline());
  }

  private Single<String> checkAccessToken() {
    return Single.<String>create(
            singleEmitter -> {
              if (null == m_accessToken) {
                synchronized (lock) {
                  /*double check*/
                  if (null == m_accessToken) {
                    try {
                      String token = getAccessToken().blockingGet();
                      singleEmitter.onSuccess(token);
                    } catch (RuntimeException e) {
                      Throwable throwable = e.getCause();
                      singleEmitter.onError(throwable != null ? throwable : e);
                    }
                  } else {
                    singleEmitter.onSuccess(m_accessToken);
                  }
                }
              } else {
                singleEmitter.onSuccess(m_accessToken);
              }
            })
        .retry(throwable -> throwable instanceof TokenException);
  }

  private <T> Single<T> checkAccessToken(
      Function<String, SingleSource<? extends JSONObject>> signalFunction, Class<T> tClass) {
    return checkAccessToken().flatMap(signalFunction).compose(toJavaObject(tClass));
  }

  private Observable<FileDownloadInfo> checkAccessToken(
      Function<String, SingleSource<? extends ResponseBody>> signalFunction) {
    return checkAccessToken()
        .flatMap(signalFunction)
        .flatMapObservable(
            responseBody -> {
              long total = responseBody.contentLength();
              InputStream inputStream = responseBody.byteStream();
              byte[] bytes = new byte[1024];
              return Observable.create(
                  emitter -> {
                    for (; ; ) {
                      int n = inputStream.read(bytes);
                      if (n == -1) {
                        emitter.onComplete();
                        break;
                      } else {
                        emitter.onNext(new FileDownloadInfo(n, total, bytes));
                      }
                    }
                  });
            });
  }
}
