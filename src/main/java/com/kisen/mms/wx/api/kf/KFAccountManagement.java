package com.kisen.mms.wx.api.kf;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.Map;

/**
 * 描述: 客服相关接口
 *
 * @author :jack.gu
 * @since : 2020/5/9
 */
public interface KFAccountManagement {
    /**
     * 添加客服帐号<br>
     * http请求方式: POST https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param kfAccount
     * @return
     */
    @POST("/customservice/kfaccount/add")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> add_kfaccount(@Query("access_token") String access_token, @Body KFAccount kfAccount);


    /**
     * 修改客服帐号<br>
     * http请求方式:  https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param kfAccount
     * @return
     */
    @POST("/customservice/kfaccount/update")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> update_kfaccount(@Query("access_token") String access_token, @Body KFAccount kfAccount);


    /**
     * 删除客服帐号<br>
     * http请求方式:  https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param kfAccount
     * @return
     */
    @POST("/customservice/kfaccount/del")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> del_kfaccount(@Query("access_token") String access_token, @Body KFAccount kfAccount);

    /**
     * 设置客服帐号的头像<br>
     * http请求方式: http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
     *
     * @param access_token
     * @param kf_account
     * @param body
     * @return
     */
    @Multipart
    @POST("/customservice/kfaccount/uploadheadimg")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> uploadheadimg(@Query("access_token") String access_token, @Query("kf_account") String kf_account, @Part MultipartBody.Part body);

    /**
     * 获取所有客服账号
     * GET https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
     */
    @GET("customservice/getkflist")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getkflist(@Query("access_token") String access_token);
}
