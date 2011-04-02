package org.leeing.hadoop.wordcount2;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @date Apr 2, 2011
 * @author leeing
 */
public class WordCount2 extends Configured implements Tool{

    
    
    public int run(String[] strings) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new WordCount2(), args);
        System.exit(res);
    }

}
