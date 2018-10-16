package com.weixin.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.weixin.init.InitConfig;
import com.weixin.model.HttpRequestModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadYamlFile {

    public static List<HttpRequestModel> load(String filePath) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        List<HttpRequestModel> conf = new ArrayList<HttpRequestModel>();

        try {
            conf = mapper.readValue(
                    new File(HttpRequestModel.class.getResource(filePath).getFile()),
                    new TypeReference<List<HttpRequestModel>>() {

                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conf;
    }


    public static InitConfig ConfigLoad(String filePath) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        InitConfig conf = new InitConfig();

        try {
            conf = mapper.readValue(
                    new File(InitConfig.class.getResource(filePath).getFile()),
                    InitConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return conf;
    }

}
