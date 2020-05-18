package com.kisen.mms.wx.push;

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
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ArticleUrlResult {
    @XmlElement
    private Integer Count;
    @XmlElementWrapper(name = "ResultList")
    @XmlElement(name = "item")
    private List<Item> ResultList;

    public Integer getCount() {
        return Count;
    }

    public ArticleUrlResult setCount(Integer count) {
        Count = count;
        return this;
    }

    public List<Item> getResultList() {
        return ResultList;
    }

    public ArticleUrlResult setResultList(List<Item> resultList) {
        ResultList = resultList;
        return this;
    }
}
