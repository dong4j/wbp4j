package com.github.echisan.wbp4j;

import com.alibaba.fastjson.JSON;
import com.github.echisan.wbp4j.Entity.PreLogin;
import com.github.echisan.wbp4j.exception.LoginFailedException;
import com.github.echisan.wbp4j.http.WbpHttpRequest;
import com.github.echisan.wbp4j.http.WbpHttpResponse;

import java.io.IOException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public abstract class AbstractLoginRequest implements LoginRequest {

    private WbpHttpRequest wbpHttpRequest;

    public AbstractLoginRequest(WbpHttpRequest wbpHttpRequest) {
        this.wbpHttpRequest = wbpHttpRequest;
    }

    public abstract Map<String, String> getPreLoginHeaders();

    public abstract Map<String, String> getPreLoginParams();

    public abstract String getPreLoginUrl();

    public abstract boolean isCacheAccountInfo();

    public abstract Map<String, String> getLoginHeaders();

    public abstract Map<String, String> getLoginParams();

    public abstract String getLoginUrl();


    @Override
    public WbpHttpResponse login(String username, String password) throws IOException, LoginFailedException {

        Map<String, String> preLoginHeaders = getPreLoginHeaders();
        Map<String, String> preLoginParams = getPreLoginParams();
        String preLoginUrl = getPreLoginUrl();

        // set username
        preLoginParams.put("su", username);

        WbpHttpResponse wbpHttpResponse = wbpHttpRequest.doPost(preLoginUrl, preLoginHeaders, preLoginParams);
        if (wbpHttpResponse.getStatusCode() != HTTP_OK) {
            throw new LoginFailedException("weibo prelogin failed..message: " + wbpHttpResponse.getBody());
        }

        PreLogin preLogin = JSON.parseObject(wbpHttpResponse.getBody(), PreLogin.class);

        Map<String, String> loginHeaders = getLoginHeaders();
        Map<String, String> loginParams = getLoginParams();
        String loginUrl = getLoginUrl();

        WbpHttpResponse loginResponse = wbpHttpRequest.doPost(loginUrl, loginHeaders, loginParams);


        return null;
    }

}
