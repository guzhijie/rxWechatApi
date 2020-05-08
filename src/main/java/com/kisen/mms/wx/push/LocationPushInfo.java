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
public class LocationPushInfo extends BasePushInfo {

    @XmlElement
    private Float Location_X;
    @XmlElement
    private Float Location_Y;
    @XmlElement
    private Float Scale;
    @XmlElement
    private String Label;
    @XmlElement
    private Long MsgId;

    public Float getLocation_X() {
        return Location_X;
    }

    public LocationPushInfo setLocation_X(Float location_X) {
        Location_X = location_X;
        return this;
    }

    public Float getLocation_Y() {
        return Location_Y;
    }

    public LocationPushInfo setLocation_Y(Float location_Y) {
        Location_Y = location_Y;
        return this;
    }

    public Float getScale() {
        return Scale;
    }

    public LocationPushInfo setScale(Float scale) {
        Scale = scale;
        return this;
    }

    public String getLabel() {
        return Label;
    }

    public LocationPushInfo setLabel(String label) {
        Label = label;
        return this;
    }

    public Long getMsgId() {
        return MsgId;
    }

    public LocationPushInfo setMsgId(Long msgId) {
        MsgId = msgId;
        return this;
    }
}
