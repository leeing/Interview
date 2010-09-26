/*
 * һ����ͨ�ı��ļ��ĸ�ʽ�ǣ�
 * field1 field2
 * field1 field2
 *
 * ������Ĺ����Ƿֱ𽫵�һ���е����ݶ�ȡ���������浽��ͬ���ļ��С�
 * �ļ�1ֻ���� field1
 * �ļ�2ֻ���� field2
 * and open the template in the editor.
 */

package filesplitter.spit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author leeing
 */
public class Splitter {
    public static void main(String[] args) throws Exception {
        splitfile("e:/testdate.txt");
    }

    public static void splitfile(String path) throws FileNotFoundException, IOException{
        String sep = System.getProperty("line.separator");
        System.out.println("Reading file :"+path+"\n");
        BufferedReader br = new BufferedReader(new FileReader(path));
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(path+".num.txt"));
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(path+".url.txt"));
        String line = br.readLine();

        while(line!=null){
            System.out.println(line);
            String contents[] = line.split("   ");
            
            bw1.write(contents[0]+ sep);
            try{
                bw2.write(contents[1]+ sep);
            }catch(Exception ex){
                bw2.write(sep);
            }
            line = br.readLine();
        }

        br.close();
        bw1.close();
        bw2.close();

        System.out.println("\nFiles are saved to :");
        System.out.println(path+".num.txt");
        System.out.println(path+".url.txt");
    }
}
