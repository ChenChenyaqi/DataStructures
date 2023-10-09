package com.BasedAscension.hashAbout;

/**
 * 一致性哈希
 *
 * 分布式数据库问题
 *
 * 用MD5算法，生成的哈希值，构成一个环
 *
 * 有三台机器，每个机器有1000个代表点，这些点通过哈希函数得出哈希值，去这个环上抢区域
 * 由于哈希函数的性质，分布是均匀的。
 * 增加机器，就均匀的把一些代表点交给新机器
 */
public class ConsistencyHash {
}
