package org.leeing.hadoop.fs;

import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

/**
 * @date Apr 7, 2011
 * @author leeing
 */
public class FileList {

    public static void main(String[] args) throws IOException {
        String uri = "hdfs://localhost:8020/user/leeing/";
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FileStatus[] status = fs.listStatus(new Path(uri));

        Path[] listedPaths = FileUtil.stat2Paths(status);
        
        for(Path p:listedPaths){
            System.out.println(p);
        }

    }
}
