package com.kisen.mms.wx.api.kf;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/5/9
 */
public class KFAccount {
    private String kf_account;
    private String nickname;
    private String password;

    public String getKf_account() {
        return kf_account;
    }

    public KFAccount setKf_account(String kf_account) {
        this.kf_account = kf_account;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public KFAccount setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public KFAccount setPassword(String password) {
        this.password = password;
        return this;
    }
}
