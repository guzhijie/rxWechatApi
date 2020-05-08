package com.kisen.mms.wx.api;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class WXResult {
    private int errcode;
    private String errmsg;

    public int getErrcode() {
        return errcode;
    }

    public WXResult setErrcode(int errcode) {
        this.errcode = errcode;
        return this;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public WXResult setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }
}
