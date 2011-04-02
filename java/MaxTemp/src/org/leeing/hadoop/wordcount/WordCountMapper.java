package org.leeing.hadoop.wordcount;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * @date Apr 2, 2011
 * @author leeing
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

    private final IntWritable ONE = new IntWritable(1);
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] tokens = line.split(" ");

        for(int i = 0;i<tokens.length;i++){
            if(!tokens[i].equals("")){
                context.write(new Text(tokens[i]), ONE);
            }
        }
        
    }

}
