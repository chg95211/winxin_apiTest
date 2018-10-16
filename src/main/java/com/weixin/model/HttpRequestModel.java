package com.weixin.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestModel {

    public HttpTestGven getGiven() {
        return given;
    }

    public void setGiven(HttpTestGven given) {
        this.given = given;
    }



    public HttpTestWhen getWhen() {
        return when;
    }

    public void setWhen(HttpTestWhen when) {
        this.when = when;
    }

    public HttpTestThen getThen() {
        return then;
    }

    public void setThen(HttpTestThen then) {
        this.then = then;
    }

    HttpTestGven given = new HttpTestGven();
    HttpTestWhen when = new HttpTestWhen();
    HttpTestThen then = new HttpTestThen();


}
