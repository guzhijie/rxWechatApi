package com.kisen.mms.wx.api.data;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
public interface UserStatistics {
    /**
     * 获取用户增减数据<br>
     * https://api.weixin.qq.com/datacube/getusersummary?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param body
     * @return
     */
    @POST("datacube/getusersummary")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getusersummary(@Query("access_token") String access_token, @Body DateInfo dateInfo);

    /**
     * 获取累计用户数据<br>
     * https://api.weixin.qq.com/datacube/getusercumulate?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param body
     * @return
     */
    @POST("datacube/getusercumulate")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getusercumulate(@Query("access_token") String access_token, @Body DateInfo dateInfo);
}
