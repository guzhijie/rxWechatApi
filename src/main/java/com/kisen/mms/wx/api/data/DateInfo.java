package com.kisen.mms.wx.api.data;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/31
 */
public class DateInfo {
    @JSONField(format = "yyyy-MM-dd")
    private Date begin_date;
    @JSONField(format = "yyyy-MM-dd")
    private Date end_date;

    public Date getBegin_date() {
        return begin_date;
    }

    public DateInfo setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
        return this;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public DateInfo setEnd_date(Date end_date) {
        this.end_date = end_date;
        return this;
    }
}
