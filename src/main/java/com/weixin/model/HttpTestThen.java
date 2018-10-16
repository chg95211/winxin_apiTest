package com.weixin.model;

import java.util.ArrayList;
import java.util.List;

public class HttpTestThen {

    int statusCode;
    List<ResponseAssertion> body = new ArrayList<ResponseAssertion>();

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<ResponseAssertion> getBody() {
        return body;
    }

    public void setBody(List<ResponseAssertion> body) {
        this.body = body;
    }
}