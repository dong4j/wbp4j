package com.github.echisan.wbp4j;

import com.github.echisan.wbp4j.exception.Wbp4jException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public abstract class AbstractUploadRequest implements UploadRequest {
    private static Logger logger = Logger.getLogger(AbstractUploadRequest.class);

    public abstract UploadResponse upload(String base64Image) throws IOException;


    public UploadResponse upload(File image) throws IOException, Wbp4jException {
        return upload(imageToBase64(image).getBytes());
    }

    @Override
    public UploadResponse upload(byte[] image) throws IOException, Wbp4jException {
        return upload(Base64.getEncoder().encode(image));
    }

    private String imageToBase64(File imageFile) {
        String base64Image = "";
        try (FileInputStream imageInFile = new FileInputStream(imageFile)) {
            // Reading a Image file from file system
            byte[] imageData = new byte[(int) imageFile.length()];
            int read = imageInFile.read(imageData);
            logger.debug("read imageFile: [" + read + "]");
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            logger.error("Image not found" + e);
        } catch (IOException ioe) {
            logger.error("Exception while reading the Image " + ioe);
        }
        return base64Image;
    }
}
