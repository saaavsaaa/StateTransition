package date.iterator.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TrieTestUtils {

    public static void printNodes(final CharNode node) {
        System.out.println(node.getValue() + ":" + node.getOriginIndex());
        Queue<CharNode> nodeQueue = new LinkedList<>();
        Collection<CharNode> showing = node.getSubNodes();
        showing.forEach(nodeQueue::offer);
        loopShowing(nodeQueue);
    }

    public static void loopShowing(final Queue<CharNode> nodeQueue) {
        Collection<CharNode> levelNodes = new ArrayList<>();
        while (!nodeQueue.isEmpty()) {
            CharNode charNode = nodeQueue.poll();
            Collection<CharNode> subShowing = charNode.getSubNodes();
            if (subShowing != null) {
                levelNodes.addAll(subShowing);
                System.out.println(charNode.getValue() + ":" + charNode.getOriginIndex());
            } else {
                System.out.println(charNode.getValue() + ":" + charNode.getOriginIndex() + ":" + charNode.getOriginLengths());
            }
        }
        if (levelNodes.isEmpty()){
            return;
        }
        levelNodes.forEach(nodeQueue::offer);
        loopShowing(nodeQueue);
    }
}
