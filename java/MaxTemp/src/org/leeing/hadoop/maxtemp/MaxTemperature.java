package org.leeing.hadoop.maxtemp;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 *
 * @author leeing
 */
public class MaxTemperature {

    public static void main(String[] args) throws Exception {

        String from = "hdfs://localhost:8020/user/leeing/maxtemp/sample.txt";
        String to = "hdfs://localhost:8020/user/leeing/maxtemp/output";

        Job job = new Job();
        job.setJarByClass(MaxTemperature.class);

        FileInputFormat.addInputPath(job, new Path(from));
        FileOutputFormat.setOutputPath(job, new Path(to));

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
