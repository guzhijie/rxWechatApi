package com.kisen.mms.wx.push;


import com.kisen.mms.wx.push.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/3/27
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
public abstract class BasePushInfo {
    @XmlRootElement(name = "xml")
    @XmlAccessorType(XmlAccessType.PROPERTY)
    public static class BasicInfo extends BasePushInfo {

    }

    @XmlElement
    private String ToUserName;
    @XmlElement
    private String FromUserName;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date CreateTime;
    @XmlJavaTypeAdapter(MSGType.MSGTypeAdapter.class)
    private MSGType MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public BasePushInfo setToUserName(String toUserName) {
        ToUserName = toUserName;
        return this;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public BasePushInfo setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
        return this;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public BasePushInfo setCreateTime(Date createTime) {
        CreateTime = createTime;
        return this;
    }

    public MSGType getMsgType() {
        return MsgType;
    }

    public BasePushInfo setMsgType(MSGType msgType) {
        MsgType = msgType;
        return this;
    }
}
