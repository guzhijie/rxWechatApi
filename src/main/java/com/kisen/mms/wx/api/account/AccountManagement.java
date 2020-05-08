package com.kisen.mms.wx.api.account;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
public interface AccountManagement {
    /**
     * 创建二维码ticket<br>
     * http请求方式: POST<br>
     * URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
     */
    @POST("qrcode/create")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> create_qrcode(@Query("access_token") String access_token, @Body QRReq qrReq);

    /**
     * 通过ticket换取二维码<br>
     * HTTP GET请求（请使用https协议）<br>
     * https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET
     * 提醒：TICKET记得进行UrlEncode<br>
     *
     * @param ticket
     * @return
     */
    @Streaming
    @GET("https://mp.weixin.qq.com/cgi-bin/showqrcode")
    @JsonAndXmlConverterFactory.Json
    Single<ResponseBody> showqrcode(@Query("ticket") String ticket);

    /**
     * 将一条长链接转成短链接
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param body
     * @return
     */
    @POST("shorturl")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> shorturl(@Query("access_token") String access_token, @Body Map<String, String> body);

}
