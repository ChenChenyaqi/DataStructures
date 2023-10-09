package com.myCompany.hashTable;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计RandomPool结构
 * 实现insert(key)：将某个key加入到该结构中，做到不重复
 * delete(key)：将原本在结构中的某个key移除
 * getRandom(): 等概率随机返回结构中的任何一个key
 * 要求：时间复杂度都是O(1)
 *
 * @author Chen Yaqi
 * @version 1.0
 */
public class RandomPool {
    public static void main(String[] args) {
        Pool pool = new Pool();
        pool.insert("A");
        pool.insert("B");
        pool.insert("C");
        pool.insert("D");
        pool.show();
        System.out.println("====");
        pool.delete("B");
        pool.show();
        System.out.println("====");
        System.out.println(pool.getRandom());
    }

    private static class Pool {
        // pool的大小
        public int size;
        public Map<String, Integer> map1;
        public Map<Integer, String> map2;

        public Pool() {
            this.map1 = new HashMap<>();
            this.map2 = new HashMap<>();
        }

        public void insert(String val){
            map1.put(val,size);
            map2.put(size,val);
            size++;
        }

        public void delete(String val){
            Integer valIndex = map1.get(val);
            // 在map1中删除
            map1.put(map2.get(size - 1), valIndex);
            map1.remove(val);
            // 在map2中删除
            map2.put(valIndex,map2.get(size - 1));
            map2.remove(size - 1);
            size--;
        }

        public String getRandom(){
            int randomNum = (int)(Math.random() * size);
            return map2.get(randomNum);
        }

        public void show(){
            for (int i = 0; i < size; i++) {
                System.out.println(map2.get(i));
            }
        }
    }
}
