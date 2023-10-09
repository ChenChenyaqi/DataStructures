package com.intermediateClass.lesson3;

import java.util.HashMap;

/**
 * 给定一个字符串类型的数组arr，求其中出现次数最多的前 k 个
 * 解法：先建立词频表，然后放入大根堆，依次弹出
 * 或 建立小根堆，限制 k 个数在里面
 * <p>
 * 假设，用户让我实现一个结构，可以接收用户给我的字符串
 * 用户可以随时调用，显示目前的 top k
 * Top 热搜系统
 */
public class TopKSystem {

    public static void main(String[] args) {
        TopHeap heap = new TopHeap(3);
        heap.add("A");
        heap.add("A");
        heap.add("B");
        heap.add("B");
        heap.add("C");
        heap.add("D");
        heap.add("D");
        heap.add("A");
        heap.printTopK(3);
    }

    // 堆上放的东西是这个node
    public static class Node {
        public String str;
        // 词频
        public int times;

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopHeap {
        public Node[] heap;
        // 堆的大小
        public int heapSize;
        // 词频map
        public HashMap<String, Node> strNodeMap;
        // key: node，value: 该字符串在arr中的位置
        public HashMap<Node, Integer> nodeIndexMap;

        // size 就是 k
        public TopHeap(int k) {
            heap = new Node[k];
            heapSize = 0;
            strNodeMap = new HashMap<>();
            nodeIndexMap = new HashMap<>();
        }

        public void add(String str) {
            Node curNode = null;
            // 默认这个node在堆上位置为-1
            int preIndex = -1;
            if (!strNodeMap.containsKey(str)) {
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                // 更新node的词频
                curNode.times++;
                // 获取node在堆上的位置
                preIndex = nodeIndexMap.get(curNode);
            }
            // 如果没有在堆上
            if (preIndex == -1) {
                // 如果堆满了
                if (heapSize == heap.length) {
                    if (heap[0].times < curNode.times) {
                        nodeIndexMap.put(heap[0], -1);
                        nodeIndexMap.put(curNode, 0);
                        heap[0] = curNode;
                        // 从 0 位置开始堆化
                        heapfiy(0);
                    }
                } else { // 没满
                    nodeIndexMap.put(curNode, heapSize);
                    heap[heapSize] = curNode;
                    heapInsert(heapSize++);
                }
            } else { // 在堆上
                heapfiy(preIndex);
            }
        }

        public void printTopK(int k) {
            if (k > heap.length) {
                System.out.println("超过了堆的大小");
            }
            for (int i = 0; i < heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.println("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        private void heapInsert(int i) {
            while (heap[i].times < heap[(i - 1) / 2].times) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        private void heapfiy(int i) {
            int left = i * 2 + 1;
            while (left < heapSize) {
                int smallest = left + 1 < heapSize && heap[left + 1].times < heap[left].times ? left + 1 : left;
                smallest = heap[smallest].times < heap[i].times ? smallest : i;
                if (smallest == i) {
                    break;
                }
                swap(smallest, i);
                i = smallest;
                left = i * 2 + 1;
            }
        }

        private void swap(int smallest, int i) {
            Node temp = heap[smallest];
            heap[smallest] = heap[i];
            heap[i] = temp;
        }


    }
}
