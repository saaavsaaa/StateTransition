package date.iterator.state;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharNodeUtil {

    /*
    * CharNode buildSubNode 中有类似代码，考虑要不要想办法合起来
    * */
    /*public static synchronized Collection<CharNode> createSubNodes(final int index, final List<String> list) {
        Map<Character, CharNode> resultNodes = new HashMap<>();
        for (String each : list) {
            char c = each.charAt(index);

            CharNode subNode;
            if (resultNodes.containsKey(c)) {
                subNode = resultNodes.get(c);
                subNode.addOriginString(each);
            } else {
                subNode = new CharNode(each, index, c);
                resultNodes.put(c, subNode);
            }
        }
        return resultNodes.values();
    }*/
}
