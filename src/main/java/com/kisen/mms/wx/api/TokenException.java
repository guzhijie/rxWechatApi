package com.kisen.mms.wx.api;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class TokenException extends Exception {

    private int errcode;

    public TokenException(int code) {
        this.errcode = code;
    }

    public int getErrcode() {
        return errcode;
    }
}
