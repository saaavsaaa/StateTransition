package date.iterator.state;

import org.junit.Test;

// 观察下print就好了，暂时先不assert
public class TrieTest {
    //                 /
    //      a          d         e
    //   b    d->d     e->e      a->a
    //   c    e->de    e->e      d->ad
    //   d->d                    e->ade
    //   e->de
    @Test
    public void testBuildTree() {
        String[] ss = new String[] {"abcde", "ade", "abc", "dee", "eade"};
        Trie trie = new Trie(ss);
        trie.build();
        TrieTestUtils.printNodes(trie.getRoot());
    }

    @Test
    public void testSearchingLargestSUX() {

    }

    @Test
    public void testNodeTree() throws Exception {
        // https://www.w3school.com.cn/tags/index.asp
        String[] ss = new String[] {"<head>", "</head>", "<body>", "</body>"};
        Trie trie = new Trie(ss);
        trie.build();
        TrieTestUtils.printNodes(trie.getRoot());
    }
}
