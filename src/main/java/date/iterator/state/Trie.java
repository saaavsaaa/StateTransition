package date.iterator.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
    private CharNode root = new CharNode(null, 0, ACConstant.Root_Value);

    private List<String> words = new ArrayList<>();

    public Trie(final String[] strings) {
        Collections.addAll(words, strings);
    }

    public CharNode searchLargestSux(final char currentChar, final CharNode currentNode) {
        //当前节点如果是第一层节点，则无最长子串
        if (currentNode.getUpNode().getValue() == ACConstant.Root_Value) {
            return null;
        }
        // 查找上层的子串，没有就递归直到根
        if (currentNode.getLargestSubStr() != null){
            // 通过上级节点的最长子串寻找current的最长子串
            for (CharNode each : currentNode.getUpNode().getLargestSubStr().getSubNodes()) {
                if (currentChar == each.getValue()) {
                    each.setTopNode(null); // 确定了最长子串的指向节点后，就不再需要判断top node了
                    return each;
                }
            }
        } else {
            // 如果直接上级无最长子串，直接匹配第一级节点，因为如果除第一级节点外还可能成为当前节点的子串，那么一定可以通过直接上级的子串找到
            for (CharNode eachTop : root.getSubNodes()) {
                if (eachTop.equals(currentNode.getTopNode())) {
                    continue;
                }
                if (currentChar == eachTop.getValue()) {
                    currentNode.setTopNode(null);
                    return eachTop;
                }
            }
        }
        currentNode.setTopNode(null);
        return null;
    }

    public void addString(final String str) {
        this.words.add(str);
    }
}
