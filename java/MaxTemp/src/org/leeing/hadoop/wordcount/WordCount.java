package org.leeing.hadoop.wordcount;

import java.io.IOException;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.leeing.hadoop.util.DirectoryUtil;

/**
 * @date Apr 2, 2011
 * @author leeing
 */
public class WordCount {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Job job = new Job();
        job.setJobName("a word count program.");

        String from = "hdfs://localhost:8020/user/leeing/wordcount";
        String to = "hdfs://localhost:8020/user/leeing/wordcount/output";

        DirectoryUtil.delete(to);

        FileInputFormat.addInputPath(job, new Path(from));
        FileOutputFormat.setOutputPath(job, new Path(to));

        job.setMapperClass(WordCountMapper.class);
        job.setCombinerClass(WordCountReducer.class);
        job.setReducerClass(WordCountReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true)?0:1);

    }
}
