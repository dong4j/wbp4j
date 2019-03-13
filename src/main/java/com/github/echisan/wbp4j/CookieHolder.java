package com.github.echisan.wbp4j;

import com.github.echisan.wbp4j.cache.CookieCache;

/**
 * 主要用于缓存cookie信息
 */
public class CookieHolder {

    private CookieCache cookieCache;

    public CookieHolder(CookieCache cookieCache) {
        this.cookieCache = cookieCache;
    }


}
