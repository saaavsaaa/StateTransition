package date.iterator.state;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ACTest {
    @Test
    public void testSearchString() {
        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        AC ac = new AC();
        ac.init(ss);
        String input = "abcdeeadeeeabceade";
        Collection<CharNode> searched = ac.search(input);
        searched.forEach(e -> {
            for (Integer originLength : e.getOriginLengths()) {
                System.out.println(e.getValue() + " : " + originLength);
            }
        });
    }

    // ade 6,15
    // abcde 0
    // abc 0,11
    // dee 3,7,16
    // eade 14
    @Test
    public void testSearchCharacter() {
        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        AC ac = new AC();
        ac.init(ss);
        String input = "abcdeeadeeeabceadee";
        for (int i = 0; i < input.length(); i++) {
            Collection<CharNode> searched = ac.search(input.charAt(i));
            for (CharNode each : searched) {
                for (Integer originLength : each.getOriginLengths()) {
                    int beginIndex = i + 1 - originLength;
                    System.out.println(input.substring(beginIndex, i + 1) + " , at index:" + beginIndex);
                }
            }
        }
    }
}
