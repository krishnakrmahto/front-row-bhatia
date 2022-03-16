package com.frontrow.springapp.controller;

import com.frontrow.springapp.pojo.ErrorResponse;
import com.frontrow.springapp.pojo.Response;

public abstract class AbstractController {
    protected <T> Response<?> getSuccessResponse(T t) {
        return new Response<>(true, t, null);
    }

    protected <T> Response<?> getFailureResponse(T t, ErrorResponse error) {
        return new Response<>(true, t, error);
    }
}
