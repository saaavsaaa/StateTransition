package date.iterator.state;

import java.util.ArrayList;
import java.util.List;

public class CharNode {
    //指向原字符串，子节点生成完毕后转移到子节点，节点又相同前缀时，可能有多个字符串
    private List<String> originStrings;

    //在原字符串中位置
    private int originIndex = -1;

    // 节点字符，root节点值为'/'
    private char value = '/';

    // 上级节点
    private CharNode upNode;

    //最长后缀
    private CharNode largestSUX;

    //原字符串长度，int数组，只有当该字符为结尾字符时有值，否则是null
    private List<Integer> originLengths = null;

    public CharNode(final String originString, final int inex) {
        if (originString != null) {
            originStrings = new ArrayList<>();
        }
        originStrings.add(originString);
        originIndex = inex;
    }

    public void setUpNode(CharNode upNode) {
        this.upNode = upNode;
    }

    public void setLargestSUX(CharNode largestSUX) {
        this.largestSUX = largestSUX;
    }

    public void setOriginLengths(List<Integer> originLengths) {
        this.originLengths = originLengths;
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

    public CharNode getLargestSUX() {
        return largestSUX;
    }

    public List<Integer> getOriginLengths() {
        return originLengths;
    }
}
