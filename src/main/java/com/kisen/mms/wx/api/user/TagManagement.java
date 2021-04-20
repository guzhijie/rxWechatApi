package com.kisen.mms.wx.api.user;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
public interface TagManagement {
    /**
     * 创建标签<br>
     * http请求方式：POST（请使用https协议）<br>
     * https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param tagInfo
     * @return
     */
    @POST("tags/create")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> create_tag(@Query("access_token") String access_token,
                                  @Body Map<String, Object> tagInfo);

    /**
     * 获取公众号已创建的标签<br>
     * http请求方式：GET（请使用https协议）<br>
     * https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @return
     */
    @GET("tags/create")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_tags(@Query("access_token") String access_token);

    /**
     * 编辑标签<br>
     * http请求方式：POST（请使用https协议）<br>
     * https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param tagInfo
     * @return
     */
    @POST("tags/update")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> update_tags(@Query("access_token") String access_token,
                                   @Body Map<String, Object> tagInfo);

    /**
     * 删除标签<br>
     * http请求方式：POST（请使用https协议）<br>
     * https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param tagInfo
     * @return
     */
    @POST("tags/delete")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> delete_tags(@Query("access_token") String access_token,
                                   @Body Map<String, Object> tagInfo);

    /**
     * 获取标签下粉丝列表<br>
     * http请求方式：GET（请使用https协议）<br>
     * https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param tagInfo
     * @return
     */
    @POST("user_mgmt/tag/get")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_tag_users(@Query("access_token") String access_token,
                                     @Body Map<String, Object> tagInfo);
}
