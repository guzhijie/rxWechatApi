package com.kisen.mms.wx.api.msg;

import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public abstract class Message<T extends Message<T>> {
    private final MsgType msgtype;
    private List<String> touser;

    protected Message(MsgType msgtype) {
        this.msgtype = msgtype;
    }

    public MsgType getMsgtype() {
        return msgtype;
    }

    public List<String> getTouser() {
        return touser;
    }

    @SuppressWarnings("unchecked")
    public T setTouser(List<String> touser) {
        this.touser = touser;
        return (T) this;
    }

}
