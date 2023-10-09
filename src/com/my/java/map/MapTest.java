package com.my.java.map;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class MapTest {
    @Test
    public void test(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(null,null);
        String put = map.put(1, "AAA");
        System.out.println(put);
        map.put(2,"AAA");
        String ccc = map.put(2, "CCC");
        System.out.println(ccc);
        map.put(3,"AAA");
        System.out.println(map);
    }
}
