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
public class LinkPushInfo extends BasePushInfo {
    @XmlElement
    private String Title;
    @XmlElement
    private String Description;
    @XmlElement
    private String Url;
    @XmlElement
    private Long MsgId;

    public String getTitle() {
        return Title;
    }

    public LinkPushInfo setTitle(String title) {
        Title = title;
        return this;
    }

    public String getDescription() {
        return Description;
    }

    public LinkPushInfo setDescription(String description) {
        Description = description;
        return this;
    }

    public String getUrl() {
        return Url;
    }

    public LinkPushInfo setUrl(String url) {
        Url = url;
        return this;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public LinkPushInfo setMsgId(Long msgId) {
        MsgId = msgId;
        return this;
    }
}
