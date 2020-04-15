package date.iterator.state;

import java.util.*;

public class AC {
    private Trie trie;
    private Queue<CharNode> currentNodes;

    public AC() {

    }

    public void init(final String[] words) {
        trie = new Trie(words);
        trie.build();
        currentNodes = new LinkedList<>();
        currentNodes.offer(trie.getRoot());
    }

    public Collection<CharNode> search(final String input) {
        Collection<CharNode> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            Collection<CharNode> searched = search(input.charAt(i));
            result.addAll(searched);
        }
        return result;
    }

    public Collection<CharNode> search(final char input) {
        Collection<CharNode> result = new ArrayList<>();
        int size = currentNodes.size();
        for (int i = 0; i < size; i++) {
            CharNode currentNode = currentNodes.poll();
            checkSubNodes(input, currentNode, result);
            if (currentNode.getLargestStrSub() != null) {
                checkSubNodes(input, currentNode.getLargestStrSub(), result);
            }
        }
        return result;
    }

    void checkSubNodes(final char input, final CharNode current, Collection<CharNode> result) {
        Map<Character, CharNode> nextPositions = current.getSubNodes();
        if (nextPositions == null) {
            currentNodes.add(trie.getRoot());
            return;
        }
        if (nextPositions.containsKey(input)) {
            CharNode searched = nextPositions.get(input);
            if (result.contains(searched)) {
                return;
            }
            collectResult(searched, result);
        }
    }

    void collectResult(final CharNode searched, Collection<CharNode> result) {
        currentNodes.offer(searched);
        if (searched.getOriginLengths() != null && !searched.getOriginLengths().isEmpty()) {
            result.add(searched);
        }
    }
}
