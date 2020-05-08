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
 * @since : 2019/12/31
 */
public interface ImageTextStatistics {
    /**
     * 获取图文群发每日数据
     * https://api.weixin.qq.com/datacube/getarticlesummary?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param dateInfo
     * @return
     */
    @POST("datacube/getarticlesummary")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getarticlesummary(@Query("access_token") String access_token, @Body DateInfo dateInfo);

    /**
     * 获取图文群发总数据
     * https://api.weixin.qq.com/datacube/getarticletotal?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param dateInfo
     * @return
     */
    @POST("datacube/getarticletotal")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getarticletotal(@Query("access_token") String access_token, @Body DateInfo dateInfo);

    /**
     * 获取图文统计数据
     * https://api.weixin.qq.com/datacube/getuserread?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param dateInfo
     * @return
     */
    @POST("datacube/getuserread")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getuserread(@Query("access_token") String access_token, @Body DateInfo dateInfo);

    /**
     * 获取图文统计分时数据
     * https://api.weixin.qq.com/datacube/getuserreadhour?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param dateInfo
     * @return
     */
    @POST("datacube/getuserreadhour")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getuserreadhour(@Query("access_token") String access_token, @Body DateInfo dateInfo);

    /**
     * 获取图文分享转发数据
     * https://api.weixin.qq.com/datacube/getusershare?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param dateInfo
     * @return
     */
    @POST("datacube/getusershare")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getusershare(@Query("access_token") String access_token, @Body DateInfo dateInfo);

    /**
     * 获取图文分享转发分时数据
     * https://api.weixin.qq.com/datacube/getusersharehour?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param dateInfo
     * @return
     */
    @POST("datacube/getusersharehour")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getusersharehour(@Query("access_token") String access_token, @Body DateInfo dateInfo);
}
