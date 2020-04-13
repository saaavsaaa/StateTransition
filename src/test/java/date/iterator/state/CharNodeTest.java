package date.iterator.state;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CharNodeTest {
    @Test
    public void testBuildSubNode() {
        List<String> ss = Arrays.asList("abcde", "ade");
        CharNode top = new CharNode(ss.get(0), 0, 'a');
        top.addOriginString(ss.get(1));
        Collection<CharNode> subNodes = top.buildSubNode();
        for (CharNode eachSub : subNodes) { // todo 构建子节点的方法可以了，需要改为广度遍历
            System.out.println("second:" + eachSub.getValue());
            loopSubNode(eachSub);
        }
    }

    private void loopSubNode(final CharNode node) {
        Collection<CharNode> subNodes = node.buildSubNode();
        for (CharNode eachSub : subNodes) {
            System.out.println("sub value:" + eachSub.getValue());
            loopSubNode(eachSub);
        }
    }
}
