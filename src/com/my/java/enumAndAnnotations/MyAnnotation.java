package com.my.java.enumAndAnnotations;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
// 1.注解声明为@interface
public @interface MyAnnotation {
    String value() default "Hello";
}
