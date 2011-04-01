package org.leeing.hadoop.mymax;

import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
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

        //delete output directory if exists.
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(to),conf);
        if(fs.exists(new Path(to))){
            fs.delete(new Path(to), true);
            System.out.println("delete :"+to);
        }
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
