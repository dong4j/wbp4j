package com.github.echisan.wbp4j.io;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class AccessPersistenceCookie {
    private static final String DEFAULT_COOKIE_FILE_NAME = "wbpcookie";
    private final String cookieFileName;

    public AccessPersistenceCookie(String cookieFileName) {
        this.cookieFileName = cookieFileName;
    }

    public AccessPersistenceCookie() {
        this.cookieFileName = DEFAULT_COOKIE_FILE_NAME;
    }

    public abstract void write(String cookie) throws IOException;

    public abstract String read() throws IOException;

    public String getCookieFileName() {
        return cookieFileName;
    }
}
