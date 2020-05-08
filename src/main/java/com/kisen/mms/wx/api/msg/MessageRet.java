package com.kisen.mms.wx.api.msg;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class MessageRet {
    private int errcode;
    private String errmsg;
    private long msg_id;

    public int getErrcode() {
        return errcode;
    }

    public MessageRet setErrcode(int errcode) {
        this.errcode = errcode;
        return this;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public MessageRet setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public long getMsg_id() {
        return msg_id;
    }

    public MessageRet setMsg_id(long msg_id) {
        this.msg_id = msg_id;
        return this;
    }
}
