package com.kisen.mms.wx.push;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 描述:
 * <MsgType><![CDATA[shortvideo]]></MsgType>
 * <MsgType><![CDATA[video]]></MsgType>
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class VideoPushInfo extends BasePushInfo {
    @XmlElement
    private Long MediaId;
    @XmlElement
    private Long MsgId;
    @XmlElement
    private Long ThumbMediaId;

    public Long getMediaId() {
        return MediaId;
    }

    public VideoPushInfo setMediaId(Long mediaId) {
        MediaId = mediaId;
        return this;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public VideoPushInfo setMsgId(Long msgId) {
        MsgId = msgId;
        return this;
    }

    public Long getThumbMediaId() {
        return ThumbMediaId;
    }

    public VideoPushInfo setThumbMediaId(Long thumbMediaId) {
        ThumbMediaId = thumbMediaId;
        return this;
    }
}
