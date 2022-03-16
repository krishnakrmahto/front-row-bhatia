package com.frontrow.springapp.pojo;

import java.beans.ConstructorProperties;
import lombok.Data;

@Data
public class Response<T> {
    private boolean success;
    private T data;
    private ErrorResponse error;

    @ConstructorProperties({"success", "data", "error"})
    public Response(boolean success, T data, ErrorResponse error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
