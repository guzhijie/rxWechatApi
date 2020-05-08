package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class VoicePushInfo extends BasePushInfo {
    @XmlElement
    private String Format;
    @XmlElement
    private Long MediaId;
    @XmlElement
    private Long MsgId;
    @XmlElement
    private String Recognition;

    public String getRecognition() {
        return Recognition;
    }

    public VoicePushInfo setRecognition(String recognition) {
        Recognition = recognition;
        return this;
    }

    public String getFormat() {
        return Format;
    }

    public VoicePushInfo setFormat(String format) {
        Format = format;
        return this;
    }

    public Long getMediaId() {
        return MediaId;
    }

    public VoicePushInfo setMediaId(Long mediaId) {
        MediaId = mediaId;
        return this;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public VoicePushInfo setMsgId(Long msgId) {
        MsgId = msgId;
        return this;
    }
}
