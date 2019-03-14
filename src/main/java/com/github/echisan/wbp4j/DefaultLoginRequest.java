package com.github.echisan.wbp4j;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DefaultLoginRequest extends AbstractLoginRequest {

    @Override
    public Map<String, String> getPreLoginHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put("Referer", "https://weibo.com/");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
        return header;
    }

    @Override
    public Map<String, String> getPreLoginParams() {
        Map<String, String> params = new HashMap<>();
        params.put("client", "ssologin.js(v1.4.19)");
        params.put("entry", "weibo");
        params.put("rsakt", "mod");
        params.put("checkpin", "1");
        params.put("_", String.valueOf(System.currentTimeMillis()));
        return params;
    }

    @Override
    public String getPreLoginUrl() {
        return "https://login.sina.com.cn/sso/prelogin.php";
    }

    @Override
    public Map<String, String> getLoginHeaders() {
        Map<String, String> header = new HashMap<>();
        header.put("Referer", "https://weibo.com/");
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36");
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("Accept", "text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8");
        header.put("Accept-Encoding", "deflate, br");
        header.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        return header;
    }

    @Override
    public Map<String, String> getLoginParams() {
        Map<String, String> params = new HashMap<>();
        params.put("encoding", "UTF-8");
        params.put("entry", "weibo");
        params.put("from", "");
        params.put("gateway", "1");
        params.put("pagerefer", "https://login.sina.com.cn/crossdomain2.php?action=logout&r=https%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%252F");
        params.put("prelt", "76");
        params.put("pwencode", "rsa2");
        params.put("qrcode_flag", "false");
        params.put("returntype", "META");
        params.put("savestate", "7");
        params.put("service", "miniblog");
        params.put("sr", "1920*1080");
        params.put("url", "https://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack");
        params.put("useticket", "1");
        params.put("vsnf", "1");
        return params;
    }

    @Override
    public String getLoginUrl() {
        return "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)";
    }
}
