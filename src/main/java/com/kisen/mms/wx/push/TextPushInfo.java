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
public class TextPushInfo extends BasePushInfo {
    @XmlElement
    private String Content;
    @XmlElement
    private Long MsgId;

    public String getContent() {
        return Content;
    }

    public TextPushInfo setContent(String content) {
        Content = content;
        return this;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public TextPushInfo setMsgId(Long msgId) {
        MsgId = msgId;
        return this;
    }
}
