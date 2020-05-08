package com.kisen.mms.wx.api.user;

import java.util.List;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2019/12/24
 */
public class UserListRet {
    private int total;
    private int count;
    private UserList data;
    private String next_openid;

    public int getTotal() {
        return total;
    }

    public UserListRet setTotal(int total) {
        this.total = total;
        return this;
    }

    public int getCount() {
        return count;
    }

    public UserListRet setCount(int count) {
        this.count = count;
        return this;
    }

    public UserList getData() {
        return data;
    }

    public UserListRet setData(UserList data) {
        this.data = data;
        return this;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public UserListRet setNext_openid(String next_openid) {
        this.next_openid = next_openid;
        return this;
    }

    public static class UserList {
        private List<String> openid;

        public List<String> getOpenid() {
            return openid;
        }

        public UserList setOpenid(List<String> openid) {
            this.openid = openid;
            return this;
        }
    }
}
