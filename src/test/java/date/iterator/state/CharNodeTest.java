package date.iterator.state;

import org.junit.Test;

import java.util.*;

public class CharNodeTest {
    @Test
    public void testBuildSubNode() {
        List<String> ss = Arrays.asList("abcde", "ade");
        CharNode top = new CharNode(ss.get(0), 0, 'a');
        top.addOriginString(ss.get(1));
        Collection<CharNode> subNodes = top.buildSubNode();
        for (CharNode eachSub : subNodes) {
            System.out.println("write second:" + eachSub.getValue());
            loopSubNode(eachSub);
        }

        printNodes(top);
    }

    private void printNodes(final CharNode node) {
        System.out.println(node.getValue() + ":" + node.getOriginIndex());
        Queue<CharNode> nodeQueue = new LinkedList<>();
        Collection<CharNode> showing = node.getSubNodes();
        showing.forEach(nodeQueue::offer);
        loopShowing(nodeQueue);
    }

    private void loopShowing(final Queue<CharNode> nodeQueue) {
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

    //loopShowing(showing, nodeQueue);
    //while (!nodeQueue.isEmpty()) {
    //    System.out.println(nodeQueue.poll().getValue());
    //}
    private Queue<CharNode> loopShowing(final Collection<CharNode> showing, final Queue<CharNode> nodeQueue) {
        if (showing.isEmpty()) {
            return nodeQueue;
        }
        Collection<CharNode> levelNodes = new ArrayList<>();
        for (CharNode charNode : showing) {
            nodeQueue.offer(charNode);
            Collection<CharNode> subShowing = charNode.getSubNodes();
            if (subShowing != null) {
                levelNodes.addAll(subShowing);
            }
        }
        return loopShowing(levelNodes, nodeQueue);
    }

    // 这里只是创建，未加子串引用，不是广度遍历顺序
    private void loopSubNode(final CharNode node) {
        Collection<CharNode> subNodes = node.buildSubNode();
        for (CharNode eachSub : subNodes) {
            System.out.println("write sub value:" + eachSub.getValue());
            if (eachSub.getOriginLengths() != null) {
                eachSub.getOriginLengths().forEach(i -> System.out.println("write " + eachSub.getValue() + ":" + i));
            }
            loopSubNode(eachSub);
        }
    }
}
