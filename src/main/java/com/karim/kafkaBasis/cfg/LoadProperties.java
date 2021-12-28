package com.karim.kafkaBasis.cfg;


import com.karim.kafkaBasis.define.CommonDefine;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by sblim
 * Date : 2021-12-28
 * Time : 오후 11:06
 */
public class LoadProperties {
    private static PropertiesConfiguration configuration;
    private static String resource = "common.properties";

    public static void loadProperties() {

        try {
            configuration = new PropertiesConfiguration(resource);

            CommonDefine.KAFKA_IP = configuration.getString("kafka_uri");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
