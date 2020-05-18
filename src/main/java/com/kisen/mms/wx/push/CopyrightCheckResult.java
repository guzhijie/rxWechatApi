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
public class CopyrightCheckResult {
    @XmlElement
    private Integer Count;
    @XmlElement
    private Integer CheckState;
    @XmlElementWrapper
    private List<Item> ResultList;

    public List<Item> getResultList() {
        return ResultList;
    }

    public CopyrightCheckResult setResultList(List<Item> resultList) {
        ResultList = resultList;
        return this;
    }

    public Integer getCount() {
        return Count;
    }

    public CopyrightCheckResult setCount(Integer count) {
        Count = count;
        return this;
    }

    public Integer getCheckState() {
        return CheckState;
    }

    public CopyrightCheckResult setCheckState(Integer checkState) {
        CheckState = checkState;
        return this;
    }
}
