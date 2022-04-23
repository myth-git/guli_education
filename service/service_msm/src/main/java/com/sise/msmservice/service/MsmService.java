package com.sise.msmservice.service;

import java.util.Map;

public interface MsmService {
    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);

    //邮箱验证码
    String getCode();
    void sendEmail(String email, String code);
}
