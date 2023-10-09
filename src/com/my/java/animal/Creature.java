package com.my.java.animal;

import java.io.Serializable;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class Creature<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }
}
