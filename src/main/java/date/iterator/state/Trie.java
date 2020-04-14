package date.iterator.state;

import java.util.*;

public class Trie {
    private CharNode root = new CharNode(null, -1, ACConstant.Root_Value);

    private List<String> words = new ArrayList<>();

    public Trie(final String[] strings) {
        Collections.addAll(words, strings);
    }

    public void build() {
        int level = 0;
        root.addSubNodes(CharNodeUtil.createSubNodes(level, words)); // addTopNode();
        addSubLevel(root.getSubNodes());
    }

    // 我怀疑字符串比较少的时候这个好，但是没测试 log n
    private void addTopNode() {
        for (String each : words) {
            char begin = each.charAt(0);
            CharNode subNode = new CharNode(each, 0, begin);
            boolean notExist = true;
            for (CharNode node : root.getSubNodes()) {
                if (node.getValue() != begin) {
                    continue;
                } else {
                    notExist = false;
                    node.addOriginString(each);
                }
            }
            if (notExist) {
                root.addSubNode(subNode);
            }
        }
    }

    private void addSubLevel(final List<CharNode> nodes) {
        List<CharNode> subNodes = new ArrayList<>();
        for (CharNode each : nodes) {
            Collection<CharNode> eachSubNodes = each.buildSubNode();
            subNodes.addAll(eachSubNodes);
            each.setUpNode(null); // todo 销毁对上级节点的引用，之后如果发现有用，就去掉这句
        }
        if (!subNodes.isEmpty()) {
            for (CharNode subNode : subNodes) {
                subNode.setLargestStrSub(searchLargestSux(subNode));
            }
            addSubLevel(subNodes);
        }
    }

    public CharNode searchLargestSux(final CharNode currentNode) {
        //当前节点如果是第一层节点，则无最长子串
        if (currentNode.getUpNode().getValue() == ACConstant.Root_Value) {
            return null;
        }
        // 查找上层的子串
        if (currentNode.getUpNode().getLargestStrSub() != null){
            // 通过上级节点的最长子串寻找current的最长子串
            for (CharNode each : currentNode.getUpNode().getLargestStrSub().getSubNodes()) {
                if (currentNode.getValue() == each.getValue()) {
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
                if (currentNode.getValue() == eachTop.getValue()) {
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

    CharNode getRoot() {
        return root;
    }
}
