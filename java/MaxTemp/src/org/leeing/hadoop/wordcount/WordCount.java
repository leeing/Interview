package org.leeing.hadoop.wordcount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.leeing.hadoop.util.DirectoryUtil;

/**
 * @date Apr 2, 2011
 * @author leeing
 */
public class WordCount extends Configured implements Tool{

    public static void main(String[] args) throws  Exception {
        
        int res = ToolRunner.run(new WordCount(),args);
        System.exit(res);

    }

    public int run(String[] strings) throws Exception {
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

        return job.waitForCompletion(true)?0:1;
    }
}
