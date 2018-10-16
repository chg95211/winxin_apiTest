package com.weixin.init;

import com.weixin.util.LoadYamlFile;
import io.restassured.RestAssured;
import io.restassured.config.ParamConfig;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;

import static com.jayway.jsonpath.JsonPath.read;

public class InitConfig {

    public Map<String, String> getHosts() {
        return hosts;
    }

    public void setHosts(Map<String, String> hosts) {
        this.hosts = hosts;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getHttpTestFilePath() {
        return httpTestFilePath;
    }

    public void setHttpTestFilePath(String httpTestFilePath) {
        this.httpTestFilePath = httpTestFilePath;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }


    Map<String, String> hosts = new HashMap<String, String>();
    String env;
    String corpid;
    String corpsecret;
    String httpTestFilePath;

    @BeforeClass
    public static void init() {

        InitConfig conf = LoadYamlFile.ConfigLoad("/data/HttpTestConfig.yaml");
        String host = conf.hosts.get(conf.getEnv());

        baseURI = host; //设置默认请求URL

        final String token = getToken(conf.corpid, conf.corpsecret);

        filters(new Filter() {
                    @Override
                    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
                        requestSpec.queryParam("access_token",token);
                        Response response = ctx.next(requestSpec, responseSpec);

                        return response;
                    }
                }
        );

    }



    public static String getToken(String corpid, String corpsecret) {

        String token = null;
        Response response = given().param("corpid", corpid).param("corpsecret", corpsecret).get("/cgi-bin/gettoken");

        String responseStr = response.prettyPrint();
        token = read(responseStr, "access_token");

        return token;

    }


}
