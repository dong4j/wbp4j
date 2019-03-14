package com.github.echisan.wbp4j;

import com.github.echisan.wbp4j.exception.Wbp4jException;
import com.github.echisan.wbp4j.http.WbpHttpResponse;
import org.junit.Test;

import java.io.IOException;

public class LoginTest {

    @Test
    public void loginTest() throws IOException, Wbp4jException {
        AbstractLoginRequest loginRequest = new DefaultLoginRequest();

        LoginRequest response = (LoginRequest) loginRequest.login("1916152345@qq.com", "Dengzhexuan123");

        System.out.println("status code ==========");
        System.out.println(response.getStatusCode());
        System.out.println("headers ======");
        System.out.println(response.getHeader());
        System.out.println("body ======");
        System.out.println(response.getBody());
    }
}
