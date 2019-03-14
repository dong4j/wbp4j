package com.github.echisan.wbp4j.cache;

import com.github.echisan.wbp4j.io.AccessPersistenceCookie;

import java.io.IOException;

/**
 * 这是一个完整的缓存类
 */
public class DefaultCookieCache extends AbstractCookieCache {

    private AccessPersistenceCookie accessPersistenceCookie;

    public DefaultCookieCache(AccessPersistenceCookie accessPersistenceCookie) {
        this.accessPersistenceCookie = accessPersistenceCookie;
    }

    @Override
    public String getCookie() throws IOException {

        String cookie = super.getCookie();
        if (cookie!=null && cookie.length()>50){
            return cookie;
        }else {
            super.setCookie(null);
        }
        return accessPersistenceCookie.read();
    }

    @Override
    public void setCookie(String cookie) throws IOException {
        if (cookie == null || cookie.equals("")){
            throw new IllegalArgumentException("cookie can not be null or empty.");
        }
        super.setCookie(cookie);
        accessPersistenceCookie.write(cookie);
    }
}
