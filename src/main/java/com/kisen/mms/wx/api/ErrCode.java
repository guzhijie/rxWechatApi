package com.kisen.mms.wx.api;

/**
 * 描述:
 *
 * @author :jack.gu
 * @since : 2020/2/26
 */
public interface ErrCode {

  /** 获取token的服务器ip不在白名单中 */
  int IP_NOT_IN_WHITE_LIST = 40164;

  /** 不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口 */
  int ACCESS_TOKEN_ILLEGAL = 40014;
  /** access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对 access_token 的详细机制说明 */
  int ACCESS_TOKEN_TIMEOUT = 42001;
}
