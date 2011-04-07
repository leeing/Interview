package org.leeing.hadoop.config;

import org.apache.hadoop.conf.Configuration;

/**
 * @date Apr 7, 2011
 * @author leeing
 */
public class TestConfig {

    public static void main(String[] args) {
        Configuration conf = new Configuration();
//        conf.addResource("config.xml");
        System.out.println(conf.get("size"));
        System.out.println(conf.get("weight"));
        System.out.println(conf.get("fs.default.name"));
    }
}
