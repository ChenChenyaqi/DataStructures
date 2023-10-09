package com.myCompany.BacktrackingAlgorithm;

/**
 * @author chenyaqi
 * @date 2021/8/4 - 15:45
 */
public class Main {
    /*
        1. 三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 hello -> hello
        2. 两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
        3. 上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
    */
    public static void main(String[] args) {
        String str = "hellosooo";
        checkWord(new StringBuilder(str));

    }

    public static void checkWord(StringBuilder sb){
        int left = 0;
        int right = 1;
        int temp = -1;
        while(right < sb.length()){
            if(sb.charAt(left) == sb.charAt(right)){
                if (temp == -1){
                    temp = left;
                    right++;
                } else {
                    right--;
                    sb.deleteCharAt(temp);
                    temp = -1;
                }
            } else{
                if (temp != -1){
                    if (sb.charAt(right-1) == sb.charAt(right)){
                        sb.deleteCharAt(right-1);
                    }
                    temp = right;
                    right++;
                } else{
                    left++;
                    right++;
                }
            }
        }
        System.out.println(sb);
    }
}
