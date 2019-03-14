package com.github.echisan.wbp4j;

import com.github.echisan.wbp4j.exception.Wbp4jException;
import com.github.echisan.wbp4j.http.WbpHttpResponse;

import java.io.IOException;

public interface LoginRequest {

    WbpHttpResponse login(String username, String password) throws IOException, Wbp4jException;

}
