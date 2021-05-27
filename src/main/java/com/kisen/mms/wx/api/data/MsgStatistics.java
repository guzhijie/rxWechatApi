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
public interface MsgStatistics {
  /**
   * 获取消息发送概况数据 https://api.weixin.qq.com/datacube/getupstreammsg?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsg")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsg(@Query("access_token") String access_token);

  /**
   * 获取消息分送分时数据 https://api.weixin.qq.com/datacube/getupstreammsghour?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsghour")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsghour(@Query("access_token") String access_token);

  /**
   * 获取消息发送周数据 https://api.weixin.qq.com/datacube/getupstreammsgweek?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsgweek")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsgweek(@Query("access_token") String access_token);

  /**
   * 获取消息发送月数据 https://api.weixin.qq.com/datacube/getupstreammsgmonth?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsgmonth")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsgmonth(@Query("access_token") String access_token);

  /**
   * 获取消息发送分布数据（getupstreammsgdist）
   * https://api.weixin.qq.com/datacube/getupstreammsgdist?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsgdist")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsgdist(@Query("access_token") String access_token);

  /**
   * 获取消息发送分布周数据（getupstreammsgdistweek）
   * https://api.weixin.qq.com/datacube/getupstreammsgdistweek?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsgdistweek")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsgdistweek(@Query("access_token") String access_token);

  /**
   * 获取消息发送分布月数据（getupstreammsgdistmonth）
   * https://api.weixin.qq.com/datacube/getupstreammsgdistmonth?access_token=ACCESS_TOKEN
   *
   * @param access_token
   * @return
   */
  @GET("datacube/getupstreammsgdistmonth")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> getupstreammsgdistmonth(@Query("access_token") String access_token);
}
