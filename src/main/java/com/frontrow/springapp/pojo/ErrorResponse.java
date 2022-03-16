package com.frontrow.springapp.pojo;

import java.beans.ConstructorProperties;
import lombok.Data;

@Data
public class ErrorResponse {
    private int code;
    private String type;
    private String message;

    @ConstructorProperties({"errorCode", "type", "message"})
    public ErrorResponse(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
