package date.iterator.state;

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
        CharNode searched = trie.getRoot();
        for (int i = 0; i < input.length(); i++) {
            searched = search(input.charAt(i), searched);
            if (searched == null) {
                searched = trie.getRoot();
                continue;
            }
            if (searched.getOriginLengths() != null) {
                for (Integer originLength : searched.getOriginLengths()) {
                    // todo 找的不全
                    System.out.println(searched.getValue() + " : " + originLength);
                    System.out.println(input.substring(i + 1 - originLength, i + 1)); //extends to the character at index {@code endIndex - 1}
                }
            }
        }
    }

    /*public void search(final char input) {
        CharNode node = trie.getRoot();
        Map<Character, CharNode> searchingNodes = node.getSubNodes();
        CharNode currentNode = trie.getRoot();
        while (currentNode.getSubNodes() != null && !currentNode.getSubNodes().isEmpty()) {
            if (currentNode.getSubNodes().containsKey(input)) {
                CharNode searched = currentNode.getSubNodes().get(input);
                currentNode = searched;
            } else {
                if (currentNode.getLargestStrSub() == null) {
                    currentNode = trie.getRoot();
                } else {
                    currentNode = currentNode.getLargestStrSub();
                }
            }
        }
    }*/

    public CharNode search(final char input, final CharNode currentNode) {
        CharNode result = null;
        if (currentNode.getSubNodes() != null && !currentNode.getSubNodes().isEmpty()) {
            if (currentNode.getSubNodes().containsKey(input)) {
                CharNode searched = currentNode.getSubNodes().get(input);
                result = searched;
            } else {
                if (currentNode.getLargestStrSub() == null) {
                    result = trie.getRoot();
                } else {
                    result = currentNode.getLargestStrSub();
                }
            }
        }
        return result;
    }
}
