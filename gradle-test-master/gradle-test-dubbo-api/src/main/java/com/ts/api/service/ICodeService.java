package com.ts.api.service;

/**
 * 验证码服务
 * @author: Owen Jia
 * @time: 2018/11/26 15:16
 */
public interface ICodeService {

    /**
     * 发送手机验证码
     * @param phoneNo
     */
    boolean sendPhoneCode(String phoneNo);
}
