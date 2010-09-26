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
 * ���ķִʵ�ʵ��
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
        String content = "Ҳ������Ϊ���縻ʿ�������ġ������ҵ���ϸ����ء��Ͷ������������й�������͹��ʱ�׼��Υ����Ͷ���������";
        List<String> result = Tokenizer.Partition(content);
        for(int i = 0;i<result.size();i++){
            System.out.print(result.get(i)+" * ");
        }
    }
}
