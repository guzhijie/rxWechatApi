package com.kisen.mms.wx.api.user;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

/**
 * 描述:用户管理
 * https://developers.weixin.qq.com/doc/offiaccount/User_Management/User_Tag_Management.html
 *
 * @author :jack.gu
 * @since : 2019/12/25
 */
public interface UserManagement {
    /**
     * 获得用户列表<br>
     * http请求方式: GET（请使用https协议）<br>
     * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
     *
     * @param access_token
     * @param next_openid
     * @return
     */
    @GET("user/get")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_users(@Query("access_token") String access_token,
                                 @Query("next_openid") String next_openid);

    /**
     * 获取用户基本信息（包括UnionID机制）<br>
     * http请求方式: GET <br>
     * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     *
     * @param access_token
     * @param openid
     * @param lang
     * @return
     */
    @GET("user/info")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_user_info(@Query("access_token") String access_token,
                                     @Query("openid") String openid,
                                     @Query("lang") String lang);

    /**
     * 批量获取用户基本信息<br>
     * http请求方式: POST <br>
     * https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param user_list
     * @return
     */
    @POST("user/info/batchget")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> batch_get_user_info(@Query("access_token") String access_token,
                                           @Body Map<String, List<UserInfoQuery>> user_list);


}
