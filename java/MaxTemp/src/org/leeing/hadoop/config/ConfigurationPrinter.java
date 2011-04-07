package org.leeing.hadoop.config;

import java.util.Map.Entry;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @date Apr 7, 2011
 * @author leeing
 */
public class ConfigurationPrinter extends Configured implements Tool{
    static {
        Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");
        Configuration.addDefaultResource("mapred-default.xml");
    }

    public int run(String[] strings) throws Exception {
        Configuration conf = getConf();
        for(Entry<String,String> entry:conf){
            System.out.printf("%s=%s\n",entry.getKey(),entry.getValue());
        }
        return 0;
    }
    
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new ConfigurationPrinter(), args);
        System.exit(res);
    }
}
