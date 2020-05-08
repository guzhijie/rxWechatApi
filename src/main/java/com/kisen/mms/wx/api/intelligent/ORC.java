package com.kisen.mms.wx.api.intelligent;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/31
 */
public interface ORC {
    /**
     * 身份证OCR识别接口<br>
     * https://api.weixin.qq.com/cv/ocr/idcard?img_url=ENCODE_URL&access_token=ACCESS_TOCKEN
     *
     * @param access_token
     * @return
     */
    @Multipart
    @POST("/cv/ocr/idcard")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> idcard(@Query("access_token") String access_token, @Part MultipartBody.Part body);

    /**
     * 银行卡OCR识别接口<br>
     * https://api.weixin.qq.com/cv/ocr/ bankcard? img_url=ENCODE_URL&access_token=ACCESS_TOCKEN
     *
     * @param access_token
     * @return
     */
    @Multipart
    @POST("/cv/ocr/bankcard")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> bankcard(@Query("access_token") String access_token, @Part MultipartBody.Part body);

    /**
     * 行驶证OCR识别接口<br>
     * https://api.weixin.qq.com/cv/ocr/ driving? img_url=ENCODE_URL&access_token=ACCESS_TOCKEN
     *
     * @param access_token
     * @return
     */
    @Multipart
    @POST("/cv/ocr/driving")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> drivingCard(@Query("access_token") String access_token, @Part MultipartBody.Part body);


    /**
     * 通用印刷体OCR识别接口<br>
     * http://api.weixin.qq.com/cv/ocr/comm?img_url=ENCODE_URL&access_token=ACCESS_TOCKEN
     *
     * @param access_token
     * @return
     */
    @Multipart
    @POST("/cv/ocr/comm")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> commCard(@Query("access_token") String access_token, @Part MultipartBody.Part body);
}
