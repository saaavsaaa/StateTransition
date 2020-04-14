package date.iterator.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TrieTestUtils {

    public static void printNodes(final CharNode node) {
        System.out.println(node.getValue() + ":" + node.getOriginIndex());
        Queue<CharNode> nodeQueue = new LinkedList<>();
        Collection<CharNode> showing = node.getSubNodes().values();
        showing.forEach(nodeQueue::offer);
        loopShowing(nodeQueue);
    }

    public static void loopShowing(final Queue<CharNode> nodeQueue) {
        Collection<CharNode> levelNodes = new ArrayList<>();
        while (!nodeQueue.isEmpty()) {
            CharNode charNode = nodeQueue.poll();
            Collection<CharNode> subShowing = null;
            if (charNode.getSubNodes() != null) {
                subShowing = charNode.getSubNodes().values();
            }
            if (subShowing != null) {
                levelNodes.addAll(subShowing);
                System.out.println(charNode.getValue() + ":" + charNode.getOriginIndex());
            } else {
                System.out.println(charNode.getValue() + ":" + charNode.getOriginIndex() + ":" + charNode.getOriginLengths());
            }
            if (charNode.getLargestStrSub() != null) {
                System.out.print("LargestStrSub:");
                printToRoot(charNode.getLargestStrSub());
                System.out.println();
            }
        }
        if (levelNodes.isEmpty()){
            return;
        }
        levelNodes.forEach(nodeQueue::offer);
        loopShowing(nodeQueue);
    }

    private static void printToRoot(CharNode node) {
        while (node.getUpNode() != null) {
            System.out.print(" < " + node.getValue());
            node = node.getUpNode();
        }
    }
}
