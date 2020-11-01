package com.kisen.mms.wx.push;

import lombok.Getter;
import lombok.Setter;

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
@Setter
@Getter
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

}
