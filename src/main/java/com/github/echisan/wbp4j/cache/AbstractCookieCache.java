package com.github.echisan.wbp4j.cache;

import java.io.Serializable;

public abstract class AbstractCookieCache implements Serializable {

    private static volatile String cookie;

    public void setCookie(String cookie){
        AbstractCookieCache.cookie = cookie;
    }

    public String getCookie(){
        return AbstractCookieCache.cookie;
    }

}
