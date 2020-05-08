package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
public enum MSGType {

    /**
     * @see EventPushInfo;
     */
    event(EventPushInfo.class),
    /**
     * @see NewsPushInfo
     */
    news(NewsPushInfo.class),
    /**
     * @see TextPushInfo;
     */
    text(TextPushInfo.class),
    /**
     * @see ImagePushInfo;
     */
    image(ImagePushInfo.class),
    /**
     * @see VoicePushInfo;
     */
    voice(VoicePushInfo.class),
    /**
     * @see VideoPushInfo;
     */
    video(VideoPushInfo.class),
    /**
     * @see VideoPushInfo
     */
    shortvideo(VideoPushInfo.class),
    /**
     * @see LocationPushInfo;
     */
    location(LocationPushInfo.class),
    /**
     * @see LinkPushInfo;
     */
    link(LinkPushInfo.class),
    /**
     * @see MusicPushInfo;
     */
    music(MusicPushInfo.class);

    private Class<? extends BasePushInfo> type;

    MSGType(Class<? extends BasePushInfo> type) {
        this.type = type;
    }

    public Class<? extends BasePushInfo> getType() {
        return type;
    }

    public static class MSGTypeAdapter extends XmlAdapter<String, MSGType> {

        @Override
        public MSGType unmarshal(String v) throws Exception {
            return MSGType.valueOf(v.trim());
        }

        @Override
        public String marshal(MSGType v) throws Exception {
            return v.toString();
        }
    }
}
