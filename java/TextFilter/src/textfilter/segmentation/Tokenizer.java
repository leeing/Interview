package textfilter.segmentation;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;

/**
 * 中文分词的实现
 */
public class Tokenizer{

    public static List<String> Partition(String input) {
 
        Analyzer analyzer = new SmartChineseAnalyzer(Version.LUCENE_30,true);
        Reader reader = new StringReader(input);
        TokenStream tokenStream = analyzer.tokenStream("", reader);
        TermAttribute termAtt = (TermAttribute) tokenStream.getAttribute(TermAttribute.class);
        //TypeAttribute typeAtt = (TypeAttribute) tokenStream.getAttribute(TypeAttribute.class);
        List<String> tokens = new ArrayList<String>();

        try {
            while (tokenStream.incrementToken()) {
                String word = termAtt.term().toString();
                tokens.add(word);
            }

            return tokens;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String content = "也正是因为诸如富士康这样的“标杆企业”严格遵守《劳动法》和深圳市公布的最低工资标准显违法，投诉相对容易";
        List<String> result = Tokenizer.Partition(content);
        for(int i = 0;i<result.size();i++){
            System.out.print(result.get(i)+" * ");
        }
    }
}
