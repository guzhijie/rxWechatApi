package com.kisen.mms.wx.api.media;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/31
 */
public interface MediaManagement {
  /**
   * 新增临时素材<br>
   * http请求方式：POST/FORM，使用https<br>
   * https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
   *
   * @param access_token
   * @param type
   * @param media
   * @return
   */
  @Multipart
  @POST("media/upload")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> upload_media(
      @Query("access_token") String access_token,
      @Query("type") String type,
      @Part MultipartBody.Part body);

  /**
   * 获取临时素材<br>
   * http请求方式: GET,https调用<br>
   * https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
   */
  @Streaming
  @GET("media/get")
  @JsonAndXmlConverterFactory.Json
  Single<ResponseBody> get_media(
      @Query("access_token") String access_token, @Query("media_id") String media_id);
}
