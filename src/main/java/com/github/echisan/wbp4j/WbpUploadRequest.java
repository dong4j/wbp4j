package com.github.echisan.wbp4j;

import com.github.echisan.wbp4j.http.WbpHttpRequest;
import com.github.echisan.wbp4j.http.WbpHttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class WbpUploadRequest extends AbstractUploadRequest {
    private WbpHttpRequest wbpHttpRequest;
    private final String url;
    private final Map<String,String> headers;
    private ParseCookieAdapter parseCookieAdapter;

    public WbpUploadRequest(WbpHttpRequest wbpHttpRequest, String url, Map<String, String> headers) {
        this.wbpHttpRequest = wbpHttpRequest;
        this.url = url;
        this.headers = headers;
    }

    public WbpUploadRequest(WbpHttpRequest wbpHttpRequest, String url) {
        this.wbpHttpRequest = wbpHttpRequest;
        this.url = url;
        this.headers = getDefaultHeaders();
    }

    @Override
    public UploadResponse upload(String base64Image) throws IOException {
        WbpHttpResponse wbpHttpResponse = wbpHttpRequest.doPostMultiPart(url, headers, base64Image);
        if (wbpHttpResponse.getStatusCode() == 200){

        }
        return null;
    }

    private Map<String,String> getDefaultHeaders(){
        Map<String, String> header = new HashMap<>();
        header.put("Host", "picupload.service.weibo.com");
        // todo get cookie
        header.put("Cookie", "");
        header.put("Origin", "https://weibo.com/");
        header.put("Referer", "https://weibo.com/");
        return header;
    }
}
