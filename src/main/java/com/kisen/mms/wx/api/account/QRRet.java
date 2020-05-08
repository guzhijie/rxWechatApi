package com.kisen.mms.wx.api.account;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/30
 */
public final class QRRet {
    private String ticket;
    private String url;
    private long expire_seconds;

    public String getTicket() {
        return ticket;
    }

    public QRRet setTicket(String ticket) {
        this.ticket = ticket;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public QRRet setUrl(String url) {
        this.url = url;
        return this;
    }

    public long getExpire_seconds() {
        return expire_seconds;
    }

    public QRRet setExpire_seconds(long expire_seconds) {
        this.expire_seconds = expire_seconds;
        return this;
    }
}
