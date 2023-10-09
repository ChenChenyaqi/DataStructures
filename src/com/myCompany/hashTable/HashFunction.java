package com.myCompany.hashTable;

/**
 *
 * 哈希函数特性：
 * 1、定义域无穷，值域有限，如：0~2^32
 * 2、相同自变量，函数值必定相等；不同自变量，函数值有几率相等，但概率极低
 * 3、函数值均匀分布
 * 4、函数值 % 一个数，结果仍均匀分布
 * 利用哈希函数解决 大数据问题：
 * 在一个40亿的数据里（都是无符号数字，且每个数字范围在int型内，0~42亿+）
 * 寻找出现次数最多的数，限制内存为 1GB
 * 如果用哈希表直接做，会内存不够。
 * Step：
 * 先求出每个数字的哈希值，然后 % 100，结果是几，就进入第几号集合里，一共100个集合
 * 由于哈希函数的特性，这100个集合里数据量是几乎相同的，即40亿/100 = 4000万
 * 再对每个集合，利用哈希表求出现次数最多的数，4000万*8字节 = 32000万字节 = 320MB大小内存
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class HashFunction {
    public static void main(String[] args) {
        
    }
}
