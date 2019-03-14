package com.github.echisan.wbp4j.cache;

import com.github.echisan.wbp4j.exception.Wbp4jException;

/**
 * 单个用户的缓存类
 */
public class SingleSinaAccount implements AccountCache {

    private static String username;
    private static String password;

    @Override
    public SinaAccount getAccount() throws Wbp4jException {
        if (username == null || password == null){
            throw new Wbp4jException("can not find any account info in cache");
        }
        return new SinaAccount(username,password);
    }

    @Override
    public void setAccount(String username, String password) {
        SingleSinaAccount.username = username;
        SingleSinaAccount.password = password;
    }


}
