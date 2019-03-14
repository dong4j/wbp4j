package com.github.echisan.wbp4j.cache;

import com.github.echisan.wbp4j.exception.Wbp4jException;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 缓存多个用户
 */
public class MultiSinaAccount implements AccountCache {

    private static CopyOnWriteArraySet<SinaAccount> accounts = new CopyOnWriteArraySet<>();

    @Override
    public SinaAccount getAccount() throws Wbp4jException {
        // todo 根据具体规则从列表中取出账号信息
        throw new Wbp4jException("un support this method now.");
    }

    @Override
    public void setAccount(String username, String password) {
        accounts.add(new SinaAccount(username, password));
    }
}
