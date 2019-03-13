package com.github.echisan.wbp4j.io;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * 本类主要用于请求或处理缓存文件
 */
public class DefaultAccessCookie extends AccessPersistenceCookie {
    private static final Logger logger = Logger.getLogger(DefaultAccessCookie.class);

    @Override
    public void write(String cookie) throws IOException {
        synchronized (this){
            File file = new File(getCookieFileName());
            if (!file.exists()){
                boolean newFile = file.createNewFile();
                if (!newFile){
                    throw new IOException("create Weibo cookie cache file failed.. file path:[ "+file.getAbsolutePath()+" ]");
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(cookie);
            fileWriter.flush();
            fileWriter.close();
        }
    }

    @Override
    public String read() throws IOException {

        File file = new File(getCookieFileName());
        if (!file.exists()){
            logger.debug("cookie cache file [ "+file.getAbsoluteFile()+" ] does not exist.");
            return null;
        }

        synchronized (this){
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String cookie = bufferedReader.readLine();
            bufferedReader.close();
            if (cookie.equals("")){
                logger.debug("cookie cache file is empty");
                return null;
            }
            return cookie;
        }
    }
}
