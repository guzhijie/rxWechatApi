package com.kisen.mms.wx.api.intelligent;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/31
 */
public interface AI {
  /**
   * 提交语音<br>
   * http请求方式: POST<br>
   * http://api.weixin.qq.com/cgi-bin/media/voice/addvoicetorecofortext?access_token=ACCESS_TOKEN&format=&voice_id=xxxxxx&lang=zh_CN
   *
   * @param access_token
   * @return
   */
  @POST("media/voice/addvoicetorecofortext")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> addvoicetorecofortext(
      @Query("access_token") String access_token,
      @Query("format") String format,
      @Query("voice_id") String voice_id,
      @Query("lang") String lang);

  /**
   * 获取语音识别结果<br>
   * http请求方式: POST
   * http://api.weixin.qq.com/cgi-bin/media/voice/queryrecoresultfortext?access_token=ACCESS_TOKEN&voice_id=xxxxxx&lang=zh_CN
   *
   * @param access_token
   * @param voice_id
   * @param lang
   * @return
   */
  @POST("media/voice/queryrecoresultfortext")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> queryrecoresultfortext(
      @Query("access_token") String access_token,
      @Query("voice_id") String voice_id,
      @Query("lang") String lang);

  /**
   * 微信翻译<br>
   * http请求方式: POST
   * http://api.weixin.qq.com/cgi-bin/media/voice/translatecontent?access_token=ACCESS_TOKEN&lfrom=xxx&lto=xxx
   *
   * @param access_token
   * @param lfrom
   * @param lto
   * @return
   */
  @POST("media/voice/translatecontent")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> translatecontent(
      @Query("access_token") String access_token,
      @Query("lfrom") String lfrom,
      @Query("lto") String lto);
}
