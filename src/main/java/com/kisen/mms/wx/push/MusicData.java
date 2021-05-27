package com.kisen.mms.wx.push;

import lombok.Getter;
import lombok.Setter;

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
@Setter
@Getter
@XmlRootElement(name = "Music")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MusicData {
  @XmlElement private String Title;
  @XmlElement private String Description;
  @XmlElement private String MusicUrl;
  @XmlElement private String HQMusicUrl;
  @XmlElement private String ThumbMediaId;
}
