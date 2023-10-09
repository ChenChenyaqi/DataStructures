package com.BasedAscension.hashAbout;

/**
 * 布隆过滤器
 *
 * 100亿个 url 黑名单，不然用户访问
 * 查找一个url是否在此黑名单里，可以添加黑名单
 *
 * 允许一定的失误率：把 白名单 误报为 黑名单。 但不会 把 黑名单 误报为 白名单
 *
 *  布隆过滤器是一个 大位图（长度 m）
 *  来一个url，调用 k 个hash函数，再 %m 得到  k 个 位置，在 布隆过滤器中对应的位置 描黑
 *
 *  公式：n = 样本量， P = 失误率
 *
 *  m = - (n * lnP) / (ln2)^2   向上取整
 *  k = ln2 * (m/n) ~= 0.7 * (m/n)  向上取整
 *
 *  P真 = 1 - e^( - (n * k真) / m真 )^k真
 */
public class BloomFilter {
    public static void main(String[] args) {
        // bit array   bit map
        // 怎么做出 bit 类型的数组
        int[] arr = new int[10];  // 32bit * 10 -> 320bits
        // arr[0]   int 0 ~ 31
        // arr[1]   int 32 ~ 63
        // arr[2]   int 64 ~ 95

        int i = 178; // 想取得 178 个bit的状态

        int numIndex = i / 32;
        int bitIndex = i % 32;

        // 拿到178位的状态
        // 这个数 右移 bitIndex位，则 178 位这个信息就 到了 这个数的最左边
        // 和 1 与 一下，这个位 是 1 则 s就是1， 是0 则 s就是0
        int s = (  (arr[numIndex] >> (bitIndex))  & 1 );

        // 把 178 位的状态改为1
        arr[numIndex] = arr[numIndex] | (1 << (bitIndex));

        // 把 178 位的状态改为0
        arr[numIndex] = arr[numIndex] & ( ~ (1 << (bitIndex)) );
    }
}
