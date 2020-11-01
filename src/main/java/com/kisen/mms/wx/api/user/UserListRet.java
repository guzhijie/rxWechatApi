package com.kisen.mms.wx.api.user;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
@Setter
@Getter
public class UserListRet {
    private int total;
    private int count;
    private UserList data;
    private String next_openid;

    @Setter
    @Getter
    public static class UserList {
        private List<String> openid;
    }
}
