package com.kisen.mms.wx.api;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public interface WXServerApi {
    String WX_API_BASE_URL = "https://api.weixin.qq.com/cgi-bin/";
    String WX_MP_BASE_URL = "https://mp.weixin.qq.com/cgi-bin/";

    /**
     * 获得Access Token<br>
     * https请求方式:<br>
     * GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     *
     * @param appid
     * @param secret
     * @return
     */
    @GET("token?grant_type=client_credential")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_access_token(@Query("appid") String appid, @Query("secret") String secret);

    /**
     * 获取所有客服账号<br>
     * http请求方式:<br>
     * GET https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @return
     */
    @GET("customservice/getkflist")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getkflist(@Query("access_token") String access_token);

    /**
     * 获取微信callback IP地址<br>
     * http请求方式:<br>
     * GET https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @return
     */
    @GET("getcallbackip")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getcallbackip(@Query("access_token") String access_token);

    /**
     * 获取微信API接口 IP地址<br>
     * http请求方式:<br>
     * GET https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @return
     */
    @GET("get_api_domain_ip")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_api_domain_ip(@Query("access_token") String access_token);

}
