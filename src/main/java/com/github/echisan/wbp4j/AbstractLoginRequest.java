package com.github.echisan.wbp4j;

import com.alibaba.fastjson.JSON;
import com.github.echisan.wbp4j.Entity.PreLogin;
import com.github.echisan.wbp4j.cache.AccountCache;
import com.github.echisan.wbp4j.cache.SingleSinaAccount;
import com.github.echisan.wbp4j.exception.LoginFailedException;
import com.github.echisan.wbp4j.exception.Wbp4jException;
import com.github.echisan.wbp4j.http.DefaultWbpHttpRequest;
import com.github.echisan.wbp4j.http.WbpHttpRequest;
import com.github.echisan.wbp4j.http.WbpHttpResponse;
import com.github.echisan.wbp4j.utils.RSAEncodeUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import static java.net.HttpURLConnection.HTTP_OK;

public abstract class AbstractLoginRequest implements LoginRequest {

    private WbpHttpRequest wbpHttpRequest;

    private AccountCache accountCache;

    public AbstractLoginRequest() {
        this(new DefaultWbpHttpRequest(), new SingleSinaAccount());
    }

    public AbstractLoginRequest(WbpHttpRequest wbpHttpRequest) {
        this(wbpHttpRequest, new SingleSinaAccount());
    }

    public AbstractLoginRequest(AccountCache accountCache) {
        this(new DefaultWbpHttpRequest(), accountCache);
    }

    public AbstractLoginRequest(WbpHttpRequest wbpHttpRequest, AccountCache accountCache) {
        this.wbpHttpRequest = wbpHttpRequest;
        this.accountCache = accountCache;
    }

    public abstract Map<String, String> getPreLoginHeaders();

    public abstract Map<String, String> getPreLoginParams();

    public abstract String getPreLoginUrl();

    public abstract Map<String, String> getLoginHeaders();

    public abstract Map<String, String> getLoginParams();

    public abstract String getLoginUrl();


    @Override
    public WbpHttpResponse login(String username, String password) throws IOException, Wbp4jException {

        accountCache.setAccount(username, password);

        // prelogin
        Map<String, String> preLoginHeaders = getPreLoginHeaders();
        Map<String, String> preLoginParams = getPreLoginParams();
        String preLoginUrl = getPreLoginUrl();

        preLoginParams.put("su", accountCache.getAccount().getUsername());

        WbpHttpResponse wbpHttpResponse = wbpHttpRequest.doGet(preLoginUrl, preLoginHeaders, preLoginParams);
        if (wbpHttpResponse.getStatusCode() != HTTP_OK) {
            throw new LoginFailedException("weibo prelogin failed..message: " + wbpHttpResponse.getBody());
        }

        PreLogin preLogin = JSON.parseObject(wbpHttpResponse.getBody(), PreLogin.class);


        // login

        Map<String, String> loginHeaders = getLoginHeaders();
        Map<String, String> loginParams = getLoginParams();

        loginParams.put("nonce", preLogin.getNonce());
        loginHeaders.put("rsakv", preLogin.getRsakv());
        loginHeaders.put("servertime", String.valueOf(preLogin.getServertime()));
        // 根据微博加密js中密码拼接的方法
        String pwd = preLogin.getServertime() + "\t" + preLogin.getNonce() + "\n" + accountCache.getAccount().getPassword();
        try {
            loginHeaders.put("sp", RSAEncodeUtils.encode(pwd, preLogin.getPubkey(), "10001"));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException |
                InvalidKeySpecException | InvalidKeyException |
                BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
            throw new LoginFailedException("password encrypt failed..");
        }
        String loginUrl = getLoginUrl();

        return wbpHttpRequest.doPost(loginUrl, loginHeaders, loginParams);
    }
}
