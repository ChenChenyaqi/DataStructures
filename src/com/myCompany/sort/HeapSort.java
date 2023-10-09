package com.myCompany.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author chenyaqi
 * @date 2021/6/13 - 14:42
 */
public class HeapSort {
    public static void main(String[] args) {
        // 要求对数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);

        // 测试堆排序正确性
        test();  // Nice!

        // 比较快排和堆排的时间
        // 测试时间
        int[] nums1 = new int[10000000];
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = (int)(Math.random() * 1000);
        }
        int[] nums2 = Arrays.copyOf(nums1, nums1.length);
        // 快排
        long before1 = System.currentTimeMillis();
        QuickSort.quickSort(nums1, 0, nums1.length - 1);
        long after1 = System.currentTimeMillis();
        long time1 = (after1 - before1);

        // 堆排
        long before2 = System.currentTimeMillis();
        heapSort(nums2);
        long after2 = System.currentTimeMillis();
        long time2 = (after2 - before2);

//        // 冒泡排
//        long before3 = System.currentTimeMillis();
//        BubbleSort.bubbleSort(nums3);
//        long after3 = System.currentTimeMillis();
//        long time3 = (after3 - before3);

        // 时间差对比
        System.out.println("time1 = " + time1);
        System.out.println("time2 = " + time2);
//        System.out.println("time3 = " + time3);

    }

    /**
     * 对arr进行堆顺序
     *
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
//        // 先把arr大根堆化，注意是调用heapInsert()
//        for (int i = 0; i < arr.length; i++) {
//            HeapUtils.heapInsert(arr, i);
//        }
        // 更快的操作
        for (int i = arr.length - 1; i >= 0; i--) {
            HeapUtils.heapify(arr, i, arr.length);
        }
        // 去除最大值并堆化
        // 最后的结果就是排好序的arr(从小到大)
        // 堆的范围，即堆中有几个数
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while(heapSize > 0){
            heapify(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }

    /**
     * 前提arr已经基本是大根堆，把在index为父节点的子树 大根堆 化
     *
     * @param arr      堆arr
     * @param index    父节点index
     * @param heapSize 堆的范围
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        // index父节点的左孩子
        int left = index * 2 + 1;
        // 判断index是否有左孩子
        while (left < heapSize) {
            // 当右孩子存在且右孩子的值大于左孩子的值时，
            // largest为右孩子下标，否则为左孩子下标
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            // 继续和父节点比较
            largest = arr[largest] > arr[index] ? largest : index;
            // 若父节点就是最大值，则不再堆化
            if (largest == index) {
                break;
            }
            // 若不是，则交换
            swap(arr, largest, index);
            // 以孩子为父节点，继续往下堆化
            index = largest;
            left = index * 2 + 1;
        }
    }

    /**
     * 交换数组中两个位置的数
     *
     * @param arr 数组arr
     * @param a   待交换数的下标
     * @param b   待交换数的下标
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // ===================test=====================================

    // 对数器方法
    public static void rightMethod(int[] arr) {
        Arrays.sort(arr);
    }

    // 测试对比
    public static void test() {
        // 测试次数
        int testTimes = 500000;
        // 数组最大长度
        int maxSize = 100;
        // 数组元素最大值
        int maxValue = 100;
        // 测试是否通过
        boolean succeed = true;
        for (int i = 0; i < testTimes; i++) {
            // 产生两个测试数据
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);
            // 两个方法分别进行
            heapSort(arr1);
            rightMethod(arr2);
            if (!isEqual(arr1, arr2)) {
                // 打印arr1
                // 打印arr2
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    // 判断是否相等
    private static boolean isEqual(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // 产生随机数据
    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] nums = new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random() * (maxValue + 1));
        }
        return nums;
    }
}

/**
 * 堆操作
 */
class HeapUtils {
    /**
     * 向数组（大根堆）arr中，插入arr[index]
     *
     * @param arr   大根堆arr
     * @param index 待插入数字的下标
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    /**
     * 前提arr已经基本是大根堆，把在index为父节点的子树 大根堆 化
     *
     * @param arr      堆arr
     * @param index    父节点index
     * @param heapSize 堆的范围
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        // index父节点的左孩子
        int left = index * 2 + 1;
        // 判断index是否有左孩子
        while (left < heapSize) {
            // 当右孩子存在且右孩子的值大于左孩子的值时，
            // largest为右孩子下标，否则为左孩子下标
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]
                    ? left + 1 : left;
            // 继续和父节点比较
            largest = arr[largest] > arr[index] ? largest : index;
            // 若父节点就是最大值，则不再堆化
            if (largest == index) {
                break;
            }
            // 若不是，则交换
            swap(arr, largest, index);
            // 以孩子为父节点，继续往下堆化
            index = largest;
            left = index * 2 + 1;
        }
    }


    /**
     * 更新堆中index处的值
     *
     * @param arr      堆arr
     * @param index    更新处
     * @param num      更新为num
     * @param heapSize 堆的范围
     */
    public static void updateHeap(int[] arr, int index, int num, int heapSize) {
        // 如果跟新后，index处的值变小了
        // 执行heapify操作
        if (arr[index] > num) {
            arr[index] = num;
            heapify(arr, index, heapSize);
        } else if (arr[index] < num) {
            // index处的值变大了
            // 执行heapInsert操作
            arr[index] = num;
            heapInsert(arr, index);
        }
    }

    /**
     * 删除堆中index下标处的值
     *
     * @param arr      堆arr
     * @param index    待删除处
     * @param heapSize 堆的范围
     */
    public static void deleteHeap(int[] arr, int index, int heapSize) {
        // 把index处的值 替换为 最后一次添加进来的数字
        swap(arr, index, heapSize - 1);
        // 堆范围缩减1
        heapSize--;
        // 从index处开始堆化
        heapify(arr, index, heapSize);
    }


    /**
     * 交换数组中两个位置的数
     *
     * @param arr 数组arr
     * @param a   待交换数的下标
     * @param b   待交换数的下标
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}

