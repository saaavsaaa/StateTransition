package date.iterator.state;

import java.util.*;

public class CharNode {
    //指向原字符串，子节点生成完毕后转移到子节点，节点又相同前缀时，可能有多个字符串
    private List<String> originStrings;

    //在原字符串中位置，由于是广度遍历，所以多个字符串的当前字符在各自字符串的偏移量相同
    private int originIndex = -1;

    // 节点字符，root节点值为'/'
    private char value = ACConstant.Root_Value;

    // 上级节点，暂时upNode只有构建树用到了后续看看能不能销毁
    private CharNode upNode;

    // 子节点，注意循环引用，现在不确定和upNode哪个更有用，写完了看其中一个能不能去掉，暂时upNode只有构建树用到了后续看看能不能销毁
    private List<CharNode> subNodes = null;

    // todo 思考一下是否用Map替换List
    private Map<Character, CharNode> sub_Nodes;

    //最长子串，最长子串最长的情况是与上级节点同层次
    private CharNode largestStrSub = null;

    //原字符串长度，int数组，只有当该字符为结尾字符时有值，否则是null
    private List<Integer> originLengths = null;

    //保存首节点引用，便于查找最长子串时排除，在每个节点的最长子串确定后销毁
    private CharNode topNode = null;

    public CharNode(final String originString, final int index, final char currentChar) {
        if (originString != null) {
            originStrings = new ArrayList<>();
            originStrings.add(originString);
        }
        originIndex = index;
        value = currentChar;
    }

    /*
    * 构建子节点
    * todo 此处只构建节点，子节点的最长子串由Trie树处理
    * */
    public Collection<CharNode> buildSubNode() {
        if (originStrings == null && originStrings.isEmpty()) {
            return null;
        }
        int nextPosition = originIndex + 1;

        Map<Character, CharNode> resultNodes = new HashMap<>();
        for (String each : originStrings) {
            char nextChar = each.charAt(nextPosition);

            CharNode subNode;
            if (resultNodes.containsKey(nextChar)) {
                subNode = resultNodes.get(nextChar);
                subNode.addOriginString(each);
            } else {
                subNode = new CharNode(each, nextPosition, nextChar);
                subNode.setUpNode(this);
                this.addSubNode(subNode);
                resultNodes.put(nextChar, subNode);
            }

            if (each.length() == nextPosition + 1) {
                subNode.addOriginLength(each.length());
                subNode.clearOriginString(each);
            } else {
                if (nextPosition == 1) {
                    subNode.setTopNode(this);
                } else {
                    subNode.setTopNode(getTopNode());
                }
            }
        }
        this.originStrings.clear(); //当前节点的子节点构建完毕，则当前节点不再需要原字符串
        this.originStrings = null;
        return resultNodes.values();
    }

    private void clearOriginString(final String originString) {
        this.originStrings.remove(originString);
        this.subNodes = null;
    }

    public void addSubNode(final CharNode node) {
        if (this.subNodes == null) {
            this.subNodes = new ArrayList<>();
        }
        this.subNodes.add(node);
    }

    public void addSubNodes(Collection<CharNode> nodes) {
        if (this.subNodes == null) {
            this.subNodes = new ArrayList<>();
        }
        this.subNodes.addAll(nodes);
    }

    public void addOriginString(final String originString) {
        this.originStrings.add(originString);
    }

    public void setUpNode(final CharNode upNode) {
        this.upNode = upNode;
    }

    public void setLargestStrSub(CharNode largestStrSub) {
        this.largestStrSub = largestStrSub;
    }

    public void addOriginLength(int length) {
        if (originLengths == null) {
            originLengths = new ArrayList<>();
        }
        this.originLengths.add(length);
    }

    public int getOriginIndex() {
        return originIndex;
    }

    public char getValue() {
        return value;
    }

    public CharNode getUpNode() {
        return upNode;
    }

    public CharNode getLargestStrSub() {
        return largestStrSub;
    }

    public List<Integer> getOriginLengths() {
        return originLengths;
    }

    public List<CharNode> getSubNodes() {
        return subNodes;
    }

    public void setTopNode(CharNode topNode) {
        this.topNode = topNode;
    }

    public CharNode getTopNode() {
        return topNode;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
