package org.leeing.hadoop.util;

import java.io.IOException;
import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * Delete output directory if exists.
 * @date Apr 1, 2011
 * @author leeing
 */
public class DirectoryUtil {

    public static void delete(String uri) {
        
        Configuration conf = new Configuration();
        try {
            FileSystem fs = FileSystem.get(URI.create(uri), conf);
            if (fs.exists(new Path(uri))) {
                fs.delete(new Path(uri), true);
                System.out.println("deleted " + uri);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
