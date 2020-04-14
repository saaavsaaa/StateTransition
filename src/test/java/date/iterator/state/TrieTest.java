package date.iterator.state;

import org.junit.Test;

// 观察下print就好了，暂时先不assert
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
