package com.kisen.mms.wx.api.msg;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class MpNewsMessage extends com.kisen.mms.wx.api.msg.Message<MpNewsMessage> {
    private MpNewsInfo mpnews;
    private int send_ignore_reprint = 0;

    public MpNewsMessage() {
        super(com.kisen.mms.wx.api.msg.MsgType.mpnews);
    }

    public MpNewsInfo getMpnews() {
        return mpnews;
    }

    public MpNewsMessage setMpnews(MpNewsInfo mpnews) {
        this.mpnews = mpnews;
        return this;
    }

    public int getSend_ignore_reprint() {
        return send_ignore_reprint;
    }

    public MpNewsMessage setSend_ignore_reprint(int send_ignore_reprint) {
        this.send_ignore_reprint = send_ignore_reprint;
        return this;
    }

    public static class MpNewsInfo {
        private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public MpNewsInfo setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }
}
