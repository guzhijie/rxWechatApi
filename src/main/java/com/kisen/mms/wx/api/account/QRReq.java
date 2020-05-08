package com.kisen.mms.wx.api.account;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
public final class QRReq {
    private Long expire_seconds;
    private String action_name;
    private ActionInfo action_info;

    public Long getExpire_seconds() {
        return expire_seconds;
    }

    public QRReq setExpire_seconds(Long expire_seconds) {
        this.expire_seconds = expire_seconds;
        return this;
    }

    public String getAction_name() {
        return action_name;
    }

    public QRReq setAction_name(String action_name) {
        this.action_name = action_name;
        return this;
    }

    public ActionInfo getAction_info() {
        return action_info;
    }

    public QRReq setAction_info(ActionInfo action_info) {
        this.action_info = action_info;
        return this;
    }
}
