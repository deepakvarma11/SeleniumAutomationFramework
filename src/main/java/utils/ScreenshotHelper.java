package utils;

import io.qameta.allure.Attachment;

public class ScreenshotHelper {

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] attachScreenshot(byte[] screenshot) {
        return screenshot;
    }

    @Attachment(value = "Request Details", type = "text/plain")
    public String attachRequestDetails(String request) {
        return request;
    }
}