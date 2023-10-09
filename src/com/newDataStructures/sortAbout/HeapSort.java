package com.newDataStructures.sortAbout;

import java.util.Arrays;


/**
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 6, -1, 0, 9, 10, 3, 0, 2};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void heapSort(int[] arr) {
        Heap heap = new Heap();
        for (int i = 0; i < arr.length; i++) {
            heap.heapInsert(arr, i);
        }
        while (!heap.isEmpty()) {
            heap.deleteHeap(arr, 0);
        }
    }

    /**
     * 数组 --->  堆： arr = [...], size = n
     * i 左节点：2 * i + 1
     * 右节点: 2 * i + 2
     * 父节点：(i - 1) / 2
     */
    static class Heap {
        public int heapSize;

        public Heap() {
            heapSize = 0;
        }

        // 向堆中加入一个元素
        public void heapInsert(int[] arr, int index) {
            // 如果我比我的父元素大，我就可以往上走
            heapSize++;
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }


        // 从某个位置往下堆化
        public void heapify(int[] arr, int index) {
            // 得到我左孩子的下标
            int left = index * 2 + 1;
            while (left < this.heapSize) {
                // 得到左右孩子中最大的那个的下标
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;

                if (largest == index) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }
        }

        // 更新堆中某个位置的值
        public void update(int[] arr, int index, int value) {
            if (arr[index] > value) {
                arr[index] = value;
                heapify(arr, index);
            } else if (arr[index] < value) {
                arr[index] = value;
                heapInsert(arr, index);
            }
        }

        // 删除堆中某个值
        public void deleteHeap(int[] arr, int index) {
            if (isEmpty()) {
                return;
            }
            swap(arr, index, heapSize - 1);
            heapSize--;
            heapify(arr, index);
        }

        private boolean isEmpty() {
            return heapSize == 0;
        }


        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
