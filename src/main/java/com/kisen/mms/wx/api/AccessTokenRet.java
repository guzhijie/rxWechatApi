package com.kisen.mms.wx.api;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class AccessTokenRet {
    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public AccessTokenRet setAccess_token(String access_token) {
        this.access_token = access_token;
        return this;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public AccessTokenRet setExpires_in(int expires_in) {
        this.expires_in = expires_in;
        return this;
    }
}
