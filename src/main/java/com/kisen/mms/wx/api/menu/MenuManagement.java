package com.kisen.mms.wx.api.menu;

import com.alibaba.fastjson.JSONObject;
import com.kisen.mms.wx.JsonAndXmlConverterFactory;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/27
 */
public interface MenuManagement {
  /**
   * 创建菜单<br>
   * http请求方式：POST（请使用https协议）<br>
   * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN
   */
  @POST("menu/create")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> create_menu(
      @Query("access_token") String access_token, @Body Map<String, List<WXButton>> menus);

  /**
   * 查询菜单<br>
   * http请求方式: GET（请使用https协议）<br>
   * https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
   */
  @GET("get_current_selfmenu_info")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> get_current_selfmenu_info(@Query("access_token") String access_token);

  /**
   * 删除菜单<br>
   * http请求方式：GET<br>
   * https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN
   */
  @GET("cust_menu/delete")
  @JsonAndXmlConverterFactory.Json
  Single<JSONObject> delete_menu(@Query("access_token") String access_token);
}
