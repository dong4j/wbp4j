package com.github.echisan.wbp4j;

import com.github.echisan.wbp4j.exception.Wbp4jException;

import java.io.File;
import java.io.IOException;

public interface UploadRequest {

    UploadResponse upload(byte[] image) throws IOException, Wbp4jException;
}
