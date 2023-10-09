package com.newDataStructures.hashfunction.md5;

public class MD5Password {
    public static void main(String[] args) {
        String s = md5Password("a123456");
        System.out.println(s);
    }

    public static String md5Password(String password){
        String[] strArr = new String[password.length()];
        for (int i = 0; i < strArr.length; i++) {
            MyMD5 myMD5 = new MyMD5(String.valueOf(password.charAt(i)));
            strArr[i] = myMD5.getMD5();
        }
        StringBuilder res = new StringBuilder();
        int k = 0;
        for (int i = 0; i < strArr.length; i++, k++) {
            if(k == 32){
                k = 0;
            }
            res.append(strArr[i].substring(k, k + 8));
        }
        return res.toString();
    }
}
