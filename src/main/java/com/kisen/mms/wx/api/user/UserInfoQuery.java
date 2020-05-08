package com.kisen.mms.wx.api.user;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class UserInfoQuery {
    private String openid;
    private String lang;

    public String getOpenid() {
        return openid;
    }

    public UserInfoQuery setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getLang() {
        return lang;
    }

    public UserInfoQuery setLang(String lang) {
        this.lang = lang;
        return this;
    }
}
