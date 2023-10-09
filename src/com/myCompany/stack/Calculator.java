package com.myCompany.stack;

/**
 * @author chenyaqi
 * @date 2021/4/5 - 8:50
 */
public class Calculator {
    public static void main(String[] args) {
        // (中缀表达式)表达式运算
        // 1.表达式：
        String expression = "70+202*6-4";
        // 2.创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 3.定义相关变量
        // 用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        // 用于拼接多位数
        String keepNum = "";
        // 将每次扫描得到的char保存到ch中
        char ch = ' ';
        // 4.开始while循环的扫描experssion
        while (true) {
            // 依次得到experssion的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么
            if (operStack.isOper(ch)) {
                // 4.1若是符号
                // 判断符号栈是否为空
                if (!operStack.isEmpty()) {
                    // 不为空
                    // 4.1.1 符号栈中有符号
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        // 进行比较，如果当前的优先级小于等于符号栈中的优先级
                        // 就在符号栈中pop出一个符号，在数栈出pop出两个数，进行运算
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 将得到的结果入数栈，
                        numStack.push(res);
                        // 当前符号入符号栈
                        operStack.push(ch);
                    } else {
                        // 4.1.2 无符号
                        // 反之，当前优先级大于栈顶优先级，直接入栈
                        operStack.push(ch);
                    }
                } else {
                    // 为空,直接入符号栈
                    operStack.push(ch);
                }
            } else {
                // 4.2 若是数字
//                numStack.push(ch - 48);  // 错误写法，"1+3" '1' => 1
                // 4.2.1 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                // 在处理数，需要向experssion的表达式的index后再看一位，如果是数，不入栈
                // 定义一个变量字符串，用于拼接

                // 处理多位数
                keepNum += ch;
                // 如果ch已经是experssion最后一位，不用入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 后一位是运算符
                        numStack.push(Integer.parseInt(keepNum));
                        // keepNum清空
                        keepNum = "";
                    }
                }
            }
            // 让index + 1 并判断是否扫描到experssion的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 5.表达式扫描完毕，顺序的从数栈和符号栈pop出相应的数字和符号进行运算
        while (true) {
            // 如果符号栈为空，则计算完成，数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        // 将数栈最后数，pop出，就是结果
        int result = numStack.pop();
        System.out.printf("表达式：%s = %d", expression, result);
    }
}

// 先创建一个栈
// 需要扩展功能
class ArrayStack2 {
    // 栈的大小
    private int maxSize;
    // 数组模拟栈
    private int[] stack;
    // top表示栈顶，初始化为-1
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 返回栈顶的值,不出栈
    public int peek() {
        return stack[top];
    }


    // 判断栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 判断栈是否空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈
    public int pop() {
        // 先判断是否空
        if (isEmpty()) {
            // 抛出异常
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 显示栈（遍历栈）
    // 遍历时，需要从栈顶开始显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    // 返回运算符的优先级,优先级使用数字表示，数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            // 假定只有加减乘除
            return -1;
        }
    }

    // 判断是否是运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        // 用于存放计算结果
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
