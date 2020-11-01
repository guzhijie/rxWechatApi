package com.kisen.mms.wx.push;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/5/14
 */
@Setter
@Getter
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ArticleUrlResult {
    @XmlElement
    private Integer Count;
    @XmlElementWrapper(name = "ResultList")
    @XmlElement(name = "item")
    private List<Item> ResultList;
}
