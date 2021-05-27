package com.kisen.mms.wx.api.webpage;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/2/18
 */
public interface WebpageManagement {
  /**
   * 通过code换取网页授权access_token<br>
   * 获取code后，请求以下链接获取access_token：<br>
   * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
   *
   * @param appid
   * @param secret
   * @param code
   * @param grant_type
   * @return
   */
  @POST("/sns/oauth2/access_token")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> oauth2(
      @Query("appid") String appid,
      @Query("secret") String secret,
      @Query("code") String code,
      @Query("grant_type") String grant_type);

  /**
   * 用户同意授权，获取code<br>
   * https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
   */
  @GET("https://open.weixin.qq.com/connect/oauth2/authorize#wechat_redirect")
  @JsonAndXmlConverterFactory.Json
  Single<ResponseBody> authorize(
      @Query("appid") String appid,
      @Query("redirect_uri") String redirect_uri,
      @Query("response_type") String response_type,
      @Query("scope") String scope,
      @Query("state") String state);
}
