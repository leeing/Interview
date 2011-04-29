package algorithms.interview.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author leeing
 * Created at Apr 29, 2011
 */
public class CountWords {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("我达");
        Matcher matcher = pattern.matcher("我我我我我我我正则表我我我我我我我我我达式"
                + " Hello World,正则表我我我我我我我我我我达式 Hello World");
        System.out.println("match? "+matcher.matches());
        System.out.println(matcher.groupCount());
        while(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
