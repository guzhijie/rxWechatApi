package com.kisen.mms.wx.api;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
@Getter
public class WXResultException extends Exception {
  private final JSONObject result;
  private final int errcode;
  private final String errmsg;

  public WXResultException(JSONObject result) {
    super(result.toJSONString());
    this.result = result;
    errcode = result.getIntValue("errcode");
    errmsg = result.getString("errmsg");
  }
}
