package com.weixin.model;

import java.util.ArrayList;
import java.util.List;

public class HttpTestGven {

    public List<GivenHeaders> getHeaders() {
        return headers;
    }

    public void setHeaders(List<GivenHeaders> headers) {
        this.headers = headers;
    }

    public List<GivenQueryParam> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(List<GivenQueryParam> queryParam) {
        this.queryParam = queryParam;
    }


    List<GivenHeaders> headers = new ArrayList<GivenHeaders>();
    List<GivenQueryParam> queryParam = new ArrayList<GivenQueryParam>();
}
