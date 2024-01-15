package com.xingyun.easypaycommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class LoadLocalConfig {

    private static final Logger logger = LoggerFactory.getLogger(LoadLocalConfig.class);

    public static Properties properties;

    static {
        logger.info("本地配置加载start=========================");
        properties = new Properties();

        try {
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream("/work/product/config/localconfig.properties"), StandardCharsets.UTF_8);
            properties.load(streamReader);

            for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
                System.out.println(objectObjectEntry.getKey() + "=" + objectObjectEntry.getValue());
            }
        } catch (IOException e) {
            logger.error("加载本地配置失败",e);
            InputStreamReader streamReader = null;
            try {
                streamReader = new InputStreamReader(new FileInputStream("C:\\project\\config\\localconfig.properties"), StandardCharsets.UTF_8);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                properties.load(streamReader);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            for (Map.Entry<Object, Object> objectObjectEntry : properties.entrySet()) {
                System.out.println(objectObjectEntry.getKey() + "=" + objectObjectEntry.getValue());
            }
        }
        logger.info("本地配置加载end=========================");


    }

    public static void main(String[] args) {
        System.out.println("===");
    }

}
