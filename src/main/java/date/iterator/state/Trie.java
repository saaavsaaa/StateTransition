package date.iterator.state;

public class Trie {
    private CharNode root = new CharNode(null, 0, ACConstant.Root_Value);

    public CharNode searchLargestSux(final char currentChar, final CharNode currentNode) {
        //第一层节点无最长后缀
        if (currentNode.getValue() == ACConstant.Root_Value) {
            return null;
        }
        // 查找上层的后缀，没有就递归直到根
        if (currentNode.getLargestSUX() != null){
            // 通过上级节点的最长后缀寻找current的最长后缀
            for (CharNode each : currentNode.getUpNode().getLargestSUX().getSubNodes()) {
                if (currentChar == each.getValue()) {
                    return each;
                }
            }
        } else {

        }
        return null;
    }
}
