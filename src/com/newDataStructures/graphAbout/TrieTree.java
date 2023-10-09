package com.newDataStructures.graphAbout;

/**
 * 前缀树  ["abc", "bdf", "bdg", ...]  => 以 root 为根的树
 */
public class TrieTree {
    // 前缀树节点
    public static class TrieNode {
        // 在加前缀树的时候，这个节点通过了多少次, 包含end
        public int pass;
        // 这个节点，是否是一个字符串的结尾节点，如果是，是多少次
        public int end;
        // 这个节点，下级的路
        public TrieNode[] nexts;  // 如果字符种类很大，用HashMap

        public TrieNode() {
            pass = 0;
            end = 0;
            // nexts[0] == null  没有走下'a'的路
            // nexts[0] != null 有走向'a'的路
            // nexts[25] != null 有走向'z'的路
            nexts = new TrieNode[26];
        }
    }

    public static class Trie{
        private TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        // 加入一个字符串
        public void insert(String word){
            if(word == null){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';  // 确定走哪条路
                if(node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        // 删除一个字符串
        public void delete(String word){
            if(search(word) == 0){
                return;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            node.pass--;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if(--node.nexts[index].pass == 0){
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;

        }

        // 查询word这个单词之前加入过几次
        public int search(String word){
            if(word == null){
                return 0;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 求 有几个 是以 pre 作为前缀的
        public int prefixNumber(String pre){
            if(pre == null){
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }


}
