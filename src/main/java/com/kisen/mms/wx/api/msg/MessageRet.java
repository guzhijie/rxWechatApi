package com.kisen.mms.wx.api.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
@Setter
@Getter
public class MessageRet {
    private int errcode;
    private String errmsg;
    private long msg_id;
}
