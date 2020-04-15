package date.iterator.state;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
    // eade 5,14
    @Test
    public void testSearchCharacter() {
        Map<String, String> expected = new HashMap<>();
        expected.put("ade", "6,15");
        expected.put("abcde", "0");
        expected.put("abc", "0,11");
        expected.put("dee", "3,7,16");
        expected.put("eade", "5,14");

        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        AC ac = new AC();
        ac.init(ss);
        String input = "abcdeeadeeeabceadee";
        Map<String, String> actual = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            if (i == 14) {
                System.out.println();
            }
            Collection<CharNode> searched = ac.search(input.charAt(i));
            for (CharNode each : searched) {
                for (Integer originLength : each.getOriginLengths()) {
                    int beginIndex = i + 1 - originLength;
                    String achieved = input.substring(beginIndex, i + 1);
                    if (actual.containsKey(achieved)) {
                        String value = actual.get(achieved);
                        value += "," + beginIndex;
                        actual.put(achieved, value);
                    } else {
                        actual.put(achieved, String.valueOf(beginIndex));
                    }
                }
            }
        }
        for (String key : actual.keySet()) {
            String expectedValue = expected.get(key);
            String actualValue = actual.get(key);
            Assert.assertEquals(expectedValue, actualValue);
        }
    }
}
