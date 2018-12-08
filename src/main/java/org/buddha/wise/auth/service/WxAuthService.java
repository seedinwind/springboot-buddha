package org.buddha.wise.auth.service;


import org.buddha.wise.JsonResult;
import org.buddha.wise.auth.model.WxUserInfo;

public interface WxAuthService {

    JsonResult<String> login(String code);

    void refreshToken(String refresh);

    WxUserInfo getWxInfo();
}
