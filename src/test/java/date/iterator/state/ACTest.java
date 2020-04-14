package date.iterator.state;

import org.junit.Test;

public class ACTest {
    @Test
    public void testSearching() {
        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        AC ac = new AC();
        ac.init(ss);
        ac.search("abcdeeadeeeabceade");
    }
}
