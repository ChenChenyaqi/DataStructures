package com.newDataStructures.hashfunction;

public class MD5Fun {
    public static void main(String[] args) {

    }

    /**
     * 1.补齐，例如715byte大小的输入，需要补齐为512byte的整数倍，即1024，最后64位表示原始数据大小，中间的为10000...
     *          若输入刚好是512，也需要补齐到1024
     * 2.分块，根据512整分为几块。   md5输出为128byte，初始化被分为四块，且填充了4个幻数， a，b，c，d
     * 3.对每一分块进行四轮压缩，分别与a，b，c，d进行与或非位运算，一轮压缩把a，b，c，d的值更新四次，四轮压缩就更新16次，最后加到md5输出值上
     * 4.合并
     *
     * @param s
     * @return
     */
    public static String md5Fun(String s){
        return "";
    }
}
