package com.kisen.mms.wx.api.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
@Setter
@Getter
public class MpNewsMessage extends com.kisen.mms.wx.api.msg.Message<MpNewsMessage> {
    private MpNewsInfo mpnews;
    private int send_ignore_reprint = 0;

    public MpNewsMessage() {
        super(com.kisen.mms.wx.api.msg.MsgType.mpnews);
    }

    @Setter
    @Getter
    public static class MpNewsInfo {
        private String media_id;
    }
}
