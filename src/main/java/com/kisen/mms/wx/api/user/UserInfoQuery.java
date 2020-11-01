package com.kisen.mms.wx.api.user;

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
public class UserInfoQuery {
    private String openid;
    private String lang;
}
