package com.kisen.mms.wx.api.data;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/31
 */
public interface InterfaceStatistics {
    /**
     * 获取接口分析数据（getinterfacesummary）	<br>
     * https://api.weixin.qq.com/datacube/getinterfacesummary?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @return
     */
    @GET("datacube/getinterfacesummary")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getinterfacesummary(@Query("access_token") String access_token);

    /**
     * 获取接口分析分时数据（getinterfacesummaryhour）<br>
     * https://api.weixin.qq.com/datacube/getinterfacesummaryhour?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @return
     */
    @GET("datacube/getinterfacesummaryhour")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> getinterfacesummaryhour(@Query("access_token") String access_token);

}
