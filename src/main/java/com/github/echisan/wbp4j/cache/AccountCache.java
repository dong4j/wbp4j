package com.github.echisan.wbp4j.cache;

import com.github.echisan.wbp4j.exception.Wbp4jException;

public interface AccountCache {

    SinaAccount getAccount() throws Wbp4jException;

    void setAccount(String username,String password);

}
