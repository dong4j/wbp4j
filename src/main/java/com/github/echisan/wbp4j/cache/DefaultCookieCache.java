package com.github.echisan.wbp4j.cache;

import com.github.echisan.wbp4j.io.AccessPersistenceCookie;

public class DefaultCookieCache extends AbstractCookieCache {

    private AccessPersistenceCookie accessPersistenceCookie;

    public DefaultCookieCache(AccessPersistenceCookie accessPersistenceCookie) {
        this.accessPersistenceCookie = accessPersistenceCookie;
    }

    @Override
    public String getCookie() {

        String cookie = super.getCookie();
        if (cookie!=null && cookie.length()>50){
            return cookie;
        }else {
            super.setCookie(null);
        }

        String read = accessPersistenceCookie.read();
        return null;
    }

    @Override
    public void setCookie(String cookie) {
        super.setCookie(cookie);
    }
}
