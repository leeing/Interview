package algorithms.interview;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 百度二面题，模式匹配
 *
 * @author leeing
 * Created at May 27, 2011
 */
public class PatternMatch {

    public static boolean isMatch(String pattern, String str) {
        if (pattern.trim().length() <= 0 || str.trim().length() <= 0) {
            throw new IllegalArgumentException("pattern and str should not be null.");
        }
        Stack<Character> patternStack = new Stack<Character>();
        Stack<Character> strStack = new Stack<Character>();

        for (int i = pattern.length() - 1; i >= 0; i--) {
            patternStack.push(pattern.charAt(i));
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            strStack.push(str.charAt(i));
        }

        while (!strStack.isEmpty() && !patternStack.isEmpty()) {
            char currentTop = patternStack.pop();
            char next = '#';

            if (!patternStack.isEmpty()) {
                next = patternStack.peek();
            }

            if (next == '*') {
                int same = 1;

                while (!patternStack.isEmpty()) {
                    if (patternStack.peek() == '*' || patternStack.peek() == currentTop) {
                        char topch = patternStack.pop();
                        if (topch == currentTop) {
                            same++;
                        }
                    }
                } // 计算*出现之后某字符最少出现的交数

                try {
                    for (int i = 0; i < same; i++) {
                        strStack.pop();
                    }
                    while (!strStack.isEmpty() && strStack.peek() == currentTop) {
                        strStack.pop();
                    } // 去除可能多余重复的与currentTop相同的字符

                } catch (EmptyStackException ex) {
                    return false;
                }
            } else if (next == '.') {
                if (strStack.isEmpty() || strStack.pop() != currentTop||strStack.isEmpty()) {
                    return false;
                }
                strStack.pop();
                patternStack.pop();
            } else {

                if (strStack.isEmpty() || strStack.pop() != currentTop) {
                    return false;
                }
            }
        }

        if(strStack.isEmpty()&&pattern.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Stack<Character> patternStack = new Stack<Character>();
        patternStack.peek();
    }
}
