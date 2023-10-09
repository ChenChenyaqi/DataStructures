package com.myCompany.graph.operation;

/**
 * 前缀树
 * 1.判断某个字符串出现过几次：如 "abc"
 * 2.判断以某个字符串为前缀的字符串有几个：如 "ab"为前缀
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class PrefixTree {

    /**
     * 前缀树的节点，前缀树的一条边代表一个字母
     */
    public static class TrieNode{
        // 表示有多少字符串经过此节点
        public int pass;
        // 表示有多少字符串以此点为末尾，即字符串末尾字母
        public int end;
        // 表示此点下一个字母有哪些，nexts[0] == null : 表示没有走向‘a’的路
        public TrieNode[] nexts;  // HashMap<Char, Node> ; TreeMap<Char, Node>

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 向前缀树中插入字符串
         * @param word 字符串
         */
        public void insert(String word){
            if (word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                // 确定走哪条路
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 在前缀树中删除word
         * @param word
         */
        public void delete(String word){
            // 前缀树中有word，才删除
            if (search(word) != 0){
                char[] chs = word.toCharArray();
                TrieNode node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    // 删没了，设为null
                    if (--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        /**
         * 在前缀树中查询word这个词出现的次数
         * @param word 单词word
         * @return word出现的次数
         */
        public int search(String word){
            if (word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        /**
         * 返回前缀树中，有几个是以pre为前缀的
         * @param pre 前缀字符串
         * @return 以pre为前缀的字符串个数
         */
        public int prefixNumber(String pre){
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }


    }


}
