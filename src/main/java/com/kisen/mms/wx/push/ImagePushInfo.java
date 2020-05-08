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
public class ImagePushInfo extends BasePushInfo {
    @XmlElement
    private String PicUrl;
    @XmlElement
    private Long MediaId;
    @XmlElement
    private Long MsgId;

    public String getPicUrl() {
        return PicUrl;
    }

    public ImagePushInfo setPicUrl(String picUrl) {
        PicUrl = picUrl;
        return this;
    }

    public Long getMediaId() {
        return MediaId;
    }

    public ImagePushInfo setMediaId(Long mediaId) {
        MediaId = mediaId;
        return this;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public ImagePushInfo setMsgId(Long msgId) {
        MsgId = msgId;
        return this;
    }
}
