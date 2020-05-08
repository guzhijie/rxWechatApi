package com.kisen.mms.wx.api.msg;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

/**
 * 描述:消息管理
 *
 * @author :jack.gu
 * @since : 2019/12/25
 */
public interface MessageManagement {
    /**
     * 根据OpenID列表群发【订阅号不可用，服务号认证后可用】<br>
     * http请求方式:POST <br>
     * https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN
     *
     * @param access_token
     * @param message
     * @return
     */
    @POST("msg_mgmt/mass/send")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> send_message(@Query("access_token") String access_token, @Body Message message);

    /**
     * 删除群发【订阅号与服务号认证后均可用】<br>
     * http请求方式: POST <br>
     * https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN
     */
    @POST("msg_mgmt/mass/delete")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> delete_message(@Query("access_token") String access_token, @Body Map<String, Long> messageParma);

    /**
     * 预览接口【订阅号与服务号认证后均可用】<br>
     * http请求方式: POST <br>
     * https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
     */
    @POST("msg_mgmt/mass/preview")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> preview_message(@Query("access_token") String access_token, @Body Map<String, Long> messageParma);

    /**
     * 获得模板ID<br>
     * http请求方式: POST<br>
     * https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
     */
    @POST("template/api_add_template")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> add_template(@Query("access_token") String access_token, @Body Map<String, String> template_id_short);

    /**
     * 获取模板列表<br>
     * http请求方式：GET<br>
     * https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
     */
    @GET("template/get_all_private_template")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> get_all_private_template(@Query("access_token") String access_token);

    /**
     * 发送模板消息<br>
     * http请求方式: POST<br>
     * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
     */
    @POST("message/template/send")
    @JsonAndXmlConverterFactory.Json
    Single<JSONObject> send_template_message(@Query("access_token") String access_token, @Body TemplateMessage templateMessage);
}
