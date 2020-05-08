package com.kisen.mms.wx.api;

import com.alibaba.fastjson.JSONObject;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class WXResultException extends Exception {
    private final JSONObject result;
    private final int errcode;
    private final String errmsg;

    public WXResultException(JSONObject result) {
        super(result.toJSONString());
        this.result = result;
        this.errcode = result.getIntValue("errcode");
        this.errmsg = result.getString("errmsg");
    }

    public JSONObject getResult() {
        return result;
    }

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
