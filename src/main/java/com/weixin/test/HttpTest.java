package com.weixin.test;

import com.weixin.init.InitConfig;
import com.weixin.model.GivenHeaders;
import com.weixin.model.GivenQueryParam;
import com.weixin.model.HttpRequestModel;
import com.weixin.model.ResponseAssertion;
import com.weixin.util.LoadYamlFile;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HttpTest extends InitConfig {
    static List<HttpRequestModel> request;

    @BeforeClass
    public static void setUp() {
        request = LoadYamlFile.load("/data/HttpTest.yaml");
    }


    @Test
    public void run() {

        for (HttpRequestModel model : request) {

            RequestSpecification requestSpecification = given().log().all();

            List<GivenHeaders> given = model.getGiven().getHeaders();
            for (GivenHeaders headers : given) {
                requestSpecification.header(headers.getKey(), headers.getValue());
            }

            List<GivenQueryParam> queryParam = model.getGiven().getQueryParam();
            Response response = null;
            if (model.getWhen().getRequest().equals("get")) {
                for (GivenQueryParam givenQueryParam : queryParam) {
                    requestSpecification.param(givenQueryParam.getKey(), givenQueryParam.getValue());
                }
                response = requestSpecification.get(model.getWhen().getUrl());
            } else if (model.getWhen().getRequest().equals("post")) {
                Map<String, Object> parame = new HashMap<String, Object>();
                for (GivenQueryParam givenQueryParam : queryParam) {
                    parame.put(givenQueryParam.getKey(), givenQueryParam.getValue());

                }
                JSONObject json = new JSONObject(parame);

                requestSpecification.body(json);
                response = requestSpecification.when().post(model.getWhen().getUrl());
            }

            System.out.println("code: " + model.getThen().getStatusCode());
            if (response != null) {
                response.then().log().all()
                        .statusCode(model.getThen().getStatusCode());

                List<ResponseAssertion> responseAssertionList = model.getThen().getBody();
                for (ResponseAssertion body : responseAssertionList) {
                    response.then()
                            .body(body.getJsonPath(), equalTo(body.getExpect()));
                }
            } else {
                System.out.println("response is null!");
            }


        }


    }

}
