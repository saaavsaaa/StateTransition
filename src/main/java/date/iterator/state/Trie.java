package date.iterator.state;

public class Trie {
    private CharNode root = new CharNode(null, 0, ACConstant.Root_Value);

    public CharNode searchLargestSux(final char currentChar, final CharNode currentNode) {
        //当前节点如果是第一层节点，则无最长后缀
        if (currentNode.getUpNode().getValue() == ACConstant.Root_Value) {
            return null;
        }
        // 查找上层的后缀，没有就递归直到根
        if (currentNode.getLargestSubStr() != null){
            // 通过上级节点的最长后缀寻找current的最长后缀
            for (CharNode each : currentNode.getUpNode().getLargestSubStr().getSubNodes()) {
                if (currentChar == each.getValue()) {
                    each.setTopNode(null); // 确定了最长子串的指向节点后，就不再需要判断top node了
                    return each;
                }
            }
        } else {
            // 如果直接上级无最长后缀，直接匹配第一级节点，因为如果除第一级节点外还可能成为当前节点的后缀，那么一定可以通过直接上级的后缀找到
            for (CharNode eachTop : root.getSubNodes()) {
                if (eachTop.equals(currentNode.getTopNode())) {
                    continue;
                }
                if (currentChar == eachTop.getValue()) {
                    currentNode.setTopNode(null); // 确定了最长子串的指向节点后，就不再需要判断top node了
                    return eachTop;
                }
            }
        }
        currentNode.setTopNode(null);
        return null;
    }
}
