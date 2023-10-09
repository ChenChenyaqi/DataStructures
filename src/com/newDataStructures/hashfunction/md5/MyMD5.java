package com.newDataStructures.hashfunction.md5;

public class MyMD5 {
    // 初始的四个变量
    private int a = 0x67452301;
    private int b = 0xefcdab89;
    private int c = 0x98badcfe;
    private int d = 0x10325476;
    // 加密的字符串
    private String str;

    public MyMD5(String str) {
        this.str = str;
    }

    // 常量ti
    // 公式:floor(abs(sin(i+1))×(2pow32)
    private final int[] K = {
            0xd76aa478, 0xe8c7b756, 0x242070db, 0xc1bdceee,
            0xf57c0faf, 0x4787c62a, 0xa8304613, 0xfd469501, 0x698098d8,
            0x8b44f7af, 0xffff5bb1, 0x895cd7be, 0x6b901122, 0xfd987193,
            0xa679438e, 0x49b40821, 0xf61e2562, 0xc040b340, 0x265e5a51,
            0xe9b6c7aa, 0xd62f105d, 0x02441453, 0xd8a1e681, 0xe7d3fbc8,
            0x21e1cde6, 0xc33707d6, 0xf4d50d87, 0x455a14ed, 0xa9e3e905,
            0xfcefa3f8, 0x676f02d9, 0x8d2a4c8a, 0xfffa3942, 0x8771f681,
            0x6d9d6122, 0xfde5380c, 0xa4beea44, 0x4bdecfa9, 0xf6bb4b60,
            0xbebfbc70, 0x289b7ec6, 0xeaa127fa, 0xd4ef3085, 0x04881d05,
            0xd9d4d039, 0xe6db99e5, 0x1fa27cf8, 0xc4ac5665, 0xf4292244,
            0x432aff97, 0xab9423a7, 0xfc93a039, 0x655b59c3, 0x8f0ccc92,
            0xffeff47d, 0x85845dd1, 0x6fa87e4f, 0xfe2ce6e0, 0xa3014314,
            0x4e0811a1, 0xf7537e82, 0xbd3af235, 0x2ad7d2bb, 0xeb86d391
    };

    // 左移位数
    private final int[] s = {
            7, 12, 17, 22, 7, 12, 17, 22, 7, 12, 17, 22, 7,
            12, 17, 22, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20, 5, 9, 14, 20,
            4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 4, 11, 16, 23, 6, 10,
            15, 21, 6, 10, 15, 21, 6, 10, 15, 21, 6, 10, 15, 21
    };

    // 主函数
    public String getMD5() {
        int[] bytes = addBit();
        // 按512位一块进行分割
        for (int i = 0; i < bytes.length / 16; i++) {
            int[] block = new int[16];
            System.arraycopy(bytes, i * 16, block, 0, 16);
            process(block);
        }
        return changeHex(a) + changeHex(b) + changeHex(c) + changeHex(d);
    }

    private String changeHex(int a) {
        String str = "";
        for (int i = 0; i < 4; i++) {
            str += String.format("%2s", Integer.toHexString(((a >> i * 8) % (1 << 8)) & 0xff)).replace(' ', '0');

        }
        return str;
    }

    // 循环压缩每一块
    private void process(int[] block) {
        int F;
        int g;
        int preA = a;
        int preB = b;
        int preC = c;
        int preD = d;
        for (int i = 0; i < 64; i++) {
            if (i < 16) {
                F = (preB & preC) | ((~preB) & preD);
                g = i;
            } else if (i < 32) {
                F = (preD & preB) | ((~preD) & preC);
                g = (5 * i + 1) % 16;
            } else if (i < 48) {
                F = preB ^ preC ^ preD;
                g = (3 * i + 5) % 16;
            } else {
                F = preC ^ (preB | (~preD));
                g = (7 * i) % 16;
            }
            int temp = preD;
            preD = preC;
            preC = preB;
            preB = preB + (((preA + F + K[i] + block[g]) << s[i]) | ((preA + F + K[i] + block[g]) >>> (32 - s[i])));
            preA = temp;
        }
        a = preA + a;
        b = preB + b;
        c = preC + c;
        d = preD + d;
    }


    // 补位
    public int[] addBit() {
        // 计算加密字符串需要多少个512位
        int count512 = ((str.length() + 8) / 64) + 1;
        // 一个int占32位，16个32位就是512位，所以16个int代表一个512
        int[] bytes = new int[count512 * 16];
        // 获取str每个字母的code值
        byte[] strByte = str.getBytes();
        for (int i = 0; i < strByte.length; i += 4) {
            String[] temp = new String[]{"","","",""};
            for (int j = i, m = 0; (j < strByte.length) && (j < i + 4); j++, m++) {
                // 把十进制转为二进制字符串
                String binaryString = Integer.toBinaryString(strByte[j]);
                if (binaryString.length() < 8) {
                    for (int n = 0; n <= 8 - binaryString.length(); n++) {
                        binaryString = "0" + binaryString;
                    }
                }
                temp[m] = binaryString;
            }
            String tempStr = "";
            for (int j = temp.length - 1; j >= 0 ; j--) {
                tempStr += temp[j];
            }
            bytes[i / 4] = Integer.valueOf(tempStr, 2);
        }
        //尾部添加1
        bytes[str.length() >> 2] |= 0x80 << ((str.length() % 4) * 8);
        // 在后64位中添加原长
        bytes[count512 * 16 - 2] = str.length() * 8;
        return bytes;
    }

    public static void main(String[] args) {
        MyMD5 myMD5 = new MyMD5("64a6w4d56a45w4d54ads51cfajiwjfqoiji2314@#$%$#34");
        String md5 = myMD5.getMD5();
        System.out.println(md5);
    }
}
