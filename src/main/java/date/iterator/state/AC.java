package date.iterator.state;

import java.util.Collection;
import java.util.Map;

public class AC {
    private Trie trie;

    public AC() {

    }

    public void init(final String[] words) {
        trie = new Trie(words);
        trie.build();
    }

    public void search(final String input) {
        for (int i = 0; i < input.length(); i++) {
            search(input.charAt(i));
        }
    }

    public void search(final char input) {
        CharNode node = trie.getRoot();
        Map<Character, CharNode> searchingNodes = node.getSubNodes();
        CharNode currentNode = trie.getRoot();
        while (currentNode.getSubNodes() != null && !currentNode.getSubNodes().isEmpty()) {
            /*for (CharNode each : searchingNodes) {
                if (each.getValue() == input) {
                    currentNode = each;
                }
            }*/
            CharNode searched = currentNode.getSubNodes().get(input);
        }
    }
}
