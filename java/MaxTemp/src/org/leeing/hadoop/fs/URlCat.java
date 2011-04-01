package org.leeing.hadoop.fs;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

/**
 * @date Apr 1, 2011
 * @author leeing
 */
public class URlCat {

    /*
     * 可以使用静态初始化块来让Hadoop识别　hdfs 前缀
     *
     */
    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    public static void main(String[] args) throws MalformedURLException, IOException {
        InputStream in = null;

        try{
            in = new URL("hdfs://localhost:8020/user/leeing/maxtemp/sample.txt").openStream();
            IOUtils.copyBytes(in, System.out, 8192,false); // 最后一个参数用来表示拷贝完后是否要关闭System.out
        }finally{
            IOUtils.closeStream(in);
            System.out.println("\nend.");
        }
    }
}
