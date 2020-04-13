package date.iterator.state;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrieTest {
    @Test
    public void testBuildTree() {
        String[] ss = new String[] {"abcde", "ade"};
        Trie trie = new Trie(ss);
        trie.build();
        TrieTestUtils.printNodes(trie.getRoot());
    }

    @Test
    public void testSearchingLargestSUX() {

    }
}
