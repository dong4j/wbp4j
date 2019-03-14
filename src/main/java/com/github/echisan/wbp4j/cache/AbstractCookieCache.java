package com.github.echisan.wbp4j.cache;

import java.io.IOException;
import java.io.Serializable;


/**
 * 这是缓存在内存中的cookie
 */
public abstract class AbstractCookieCache implements Serializable {

    private static volatile String cookie;

    public void setCookie(String cookie) throws IOException {
        AbstractCookieCache.cookie = cookie;
    }

    public String getCookie() throws IOException {
        return AbstractCookieCache.cookie;
    }

}
