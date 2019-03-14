package com.github.echisan.wbp4j.cache;


import java.io.IOException;

/**
 * 只将cookie缓存在内存中而不持久化
 */
public class NonSerializableCookieCache extends AbstractCookieCache {

    @Override
    public void setCookie(String cookie) throws IOException {
        super.setCookie(cookie);
    }

    @Override
    public String getCookie() throws IOException {
        return super.getCookie();
    }
}
