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
public class VoiceMessage extends Message<VoiceMessage> {
    private VoiceInfo voice;

    public VoiceMessage() {
        super(MsgType.voice);
    }

    @Setter
    @Getter
    public static class VoiceInfo {
        private String media_id;
    }
}
