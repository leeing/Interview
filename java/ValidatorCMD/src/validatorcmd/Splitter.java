package validatorcmd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leeing
 */
public class Splitter {

    public static void main(String[] args) throws Exception {
        if(args.length != 1){
            System.err.println("please input a valid filepath.");
        }else{
            new Splitter().splitfile(args[0]);
        }
    }

    public boolean splitfile(String path) throws FileNotFoundException, IOException, InterruptedException {

        System.out.println("Reading file :" + path + "\n");
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = br.readLine();
        
        while (line != null) {
            String contents[] = line.split(":");
            String file = contents[0] + ":" + contents[1];

            if (file.endsWith(".java") || file.endsWith(".html")) {
                StringBuilder sb = new StringBuilder("");
                for (int i = 3; i < contents.length; i++) {
                    sb.append(contents[i]).append(":");
                }

                Pattern pattern = Pattern.compile("[\"']http://.*[\"']");
                Matcher matcher = pattern.matcher(sb.toString());
                if (matcher.find()) {
                    String urls[] = matcher.group().split("[\"' ]");
                    String url = urls[1];

                    if (url.length() > 9 && !url.contains("+") && url.contains(".")) {
                        new Thread(new Validate(file, contents[2], url)).start();
                    }
                } else {
                }
            }
            line = br.readLine();
        }
        return true;
    }
}
