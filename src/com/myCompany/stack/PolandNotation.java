package com.myCompany.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰计算器的实现
 *
 * @author chenyaqi
 * @date 2021/5/3 - 18:03
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 逆波兰表达式 (3+4)*5 - 6  === 29
        // 4 * 5 - 8 + 60 + 8 / 2  =>  4 5 * 8 - 60 + 8 2 / +
//        String suffixExpression = "3 4 + 5 * 6 -";
//        System.out.println(convertToSuffixExpression("1+((2+3)*4)-5"));
        String suffixExpression = convertToSuffixExpression("(3+4)*5-6");
        List<String> list = getListString(suffixExpression);
        System.out.println("list = " + list);
        int res = calculate(list);
        System.out.println("res = " + res);

    }

    /**
     * 逆波兰计算器
     *
     * @param list 后缀表达式的list集合
     * @return 结果，暂时为整型
     */
    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {
                stack.add(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("表达式里含有非法符号！");
                }
                stack.add("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 后缀表达式以" "分割并转为ArrayList集合
     *
     * @param suffixExpression 后缀表达式
     * @return ArrayList
     */
    private static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        return new ArrayList<>(Arrays.asList(split));
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param expression 中缀表达式
     * @return 后缀表达式
     */
    private static String convertToSuffixExpression(String expression) {
        // 1+((2+3)*4)-5
        /*
        1)初始化两个栈：运算符栈s1和储存中间结果的栈s2；
        2)从左至右扫描中缀表达式；
        3)遇到操作数时，将其压s2；
        4)遇到运算符时，比较其与s1栈顶运算符的优先级：
            1 .如果s1为空，或栈顶运算符为左括号“("，则直接将此运算符入栈；
            2 .否则，若优先级比栈顶运算符的高，也将运算符压入s1；
            3 .否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
        5)遇到括号时：
            1.如果是左括号“("，则直接压入s1
            2.如果是右括号")”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
        6)重复步骤2至5,直到表达式的最右边
        7)将s1中剩余的运算符依次弹出并压入s2
        8)依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
        */

        // 处理表达式
        List<String> list = toInfixExpressionList(expression);
        System.out.println("list = " + list.toString());
        // 声明两个栈
        Stack<String> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();
        for (String item : list) {
            // 遇到操作数时，将其压s2；
            if (item.matches("\\d+")) {
                stack2.push(item);
            } else if (isOperator(item)) {
                while (true) {
                    // 遇到运算符时，比较其与s1栈顶运算符的优先级
                    if (stack1.isEmpty() || stack1.peek().equals("(")) {
                        // 1 .如果s1为空，或栈顶运算符为左括号“("，则直接将此运算符入栈；
                        stack1.push(item);
                        break;
                    } else if (checkPriority(item, stack1.peek())) {
                        // 2 .否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                        stack1.push(item);
                        break;
                    } else {
                        // 3 .否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较；
                        stack2.push(stack1.pop());
                    }
                }
            } else {
                //遇到括号时：
                if (item.equals("(")) {
                    // 1.如果是左括号“("，则直接压入s1
                    stack1.push(item);
                } else if (item.equals(")")) {
                    // 2.如果是右括号")”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为止，此时将这一对括号丢弃
                    while (true) {
                        String t = stack1.pop();
                        if (t.equals("(")) {
                            break;
                        }
                        stack2.push(t);
                    }
                }
            }
        }
        // 将s1中剩余的运算符依次弹出并压入s2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // 把stack2里的内容转换为字符串
        while (!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        String res = "";
        while(!stack1.isEmpty()){
            res += stack1.pop();
            res += " ";
        }
        return res;
    }

    /**
     * 处理中缀表达式
     * @param expression 中缀表达式
     * @return 中缀表达式的list形式
     */
    private static List<String> toInfixExpressionList(String expression) {
        List<String> ls = new ArrayList<>();
        // 指针
        int i = 0;
        // 用于拼接
        String str;
        // 中缀表达式每个位置的char值
        char c;
        do{
            // 是运算符
            c = expression.charAt(i);
            if (c  < 48 || c > 57){
                ls.add("" + c);
                i++;
            } else {
                str="";
                while (i < expression.length() && (c= expression.charAt(i)) >= 48 && (c= expression.charAt(i))<=57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < expression.length());

        return ls;
    }

    /**
     * 判断一个字符串是不是运算符, + - * "/"
     *
     * @param item 字符串
     * @return true表示是运算符，false表示不是
     */
    private static boolean isOperator(String item) {
        return item.equals("+") || item.equals("-") || item.equals("*") || item.equals("/");
    }

    /**
     * 比较两个运算符的优先级
     *
     * @param operator1 运算符1
     * @param operator2 运算符2
     * @return 若运算符1优先级高，则返回true，反之返回false
     */
    private static boolean checkPriority(String operator1, String operator2) {
        return (operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"));
    }
}
