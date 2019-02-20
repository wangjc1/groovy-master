package com.ts.api.service.impl;

import com.ts.api.service.ICodeService;

/**
 * @author: Owen Jia
 * @time: 2018/11/26 17:04
 */
public class CodeServiceImpl implements ICodeService {
    @Override
    public boolean sendPhoneCode(String phoneNo) {
        System.out.println("i am a test api<sendPhoneCode>. phoneNo=" + phoneNo);
        return true;
    }
}
