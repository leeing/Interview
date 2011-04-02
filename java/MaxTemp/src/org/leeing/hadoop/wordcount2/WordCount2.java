package org.leeing.hadoop.wordcount2;

import java.util.HashSet;
import java.util.Set;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @date Apr 2, 2011
 * @author leeing
 */
public class WordCount2 extends Configured implements Tool{

    public static class Map extends Mapper<LongWritable, Text, Text, Object>{
        static enum Counter {INPUT_WORDS}
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();
        private boolean caseSensitive = true;
        private Set<String> patternsToSkip = new HashSet<String>();
        private long numRecords = 0;
        private String inputFile;

        @Override
        public void setup(Context context){
            Configuration conf = context.getConfiguration();
            caseSensitive = conf.getBoolean("wordcount.case.sensitive", true);
            inputFile = conf.get("mapreduce.map.input.file");
            if(conf.getBoolean("wordcount.skip.patterns", false)){
                Path[] patternFiles = new Path[0];
            }
        }

    }
    
    public int run(String[] strings) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new WordCount2(), args);
        System.exit(res);
    }

}
