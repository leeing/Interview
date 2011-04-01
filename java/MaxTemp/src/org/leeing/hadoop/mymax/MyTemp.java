package org.leeing.hadoop.mymax;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @date Apr 1, 2011
 * @author leeing
 */
public class MyTemp {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = new Job();

        String from = "hdfs://localhost:8020/user/leeing/maxtemp/sample.txt";
        String to = "hdfs://localhost:8020/user/leeing/maxtemp/myOutput";

        job.setJobName("Find the max temerature in the data set.");

        FileInputFormat.addInputPath(job, new Path(from));
        FileOutputFormat.setOutputPath(job, new Path(to));

        job.setMapperClass(MyMaxMapper.class);
        job.setReducerClass(MyMaxReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
