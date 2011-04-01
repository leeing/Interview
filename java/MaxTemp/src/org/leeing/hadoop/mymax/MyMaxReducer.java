package org.leeing.hadoop.mymax;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

/**
 * @date Apr 1, 2011
 * @author leeing
 */
public class MyMaxReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int maxTemp = Integer.MIN_VALUE;
        for(IntWritable value:values){
            maxTemp = Math.max(maxTemp, value.get());
        }

        context.write(key, new IntWritable(maxTemp));
    }

}
