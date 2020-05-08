package com.kisen.mms.wx.api.msg;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class VoiceMessage extends Message<VoiceMessage> {
    private VoiceInfo voice;

    public VoiceMessage() {
        super(MsgType.voice);
    }

    public VoiceInfo getVoice() {
        return voice;
    }

    public VoiceMessage setVoice(VoiceInfo voice) {
        this.voice = voice;
        return this;
    }

    public static class VoiceInfo {
        private String media_id;

        public String getMedia_id() {
            return media_id;
        }

        public VoiceInfo setMedia_id(String media_id) {
            this.media_id = media_id;
            return this;
        }
    }

}
