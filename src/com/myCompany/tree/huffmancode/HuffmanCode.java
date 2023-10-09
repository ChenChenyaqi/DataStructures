package com.myCompany.tree.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author chenyaqi
 * @date 2021/6/19 - 16:43
 */
public class HuffmanCode {
    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        byte[] bytes = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(bytes));
//        byte[] sourceByte = decode(huffmanCodes, bytes);
//        System.out.println(Arrays.toString(sourceByte));
//        System.out.println(new String(sourceByte));
        String srcPath = "C:\\Users\\lenovo\\Pictures\\matlab笔记.png";
        String resPath = "C:\\Users\\lenovo\\Pictures\\matlab笔记2.zip";
        String resPath2 = "C:\\Users\\lenovo\\Pictures\\matlab笔记3.png";
//        zipFile(srcPath,resPath);
        unZipFile(resPath,resPath2);
    }


    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 要解压到哪里去
     */
    public static void unZipFile(String zipFile,String dstFile){
        // 文件输入流
        FileInputStream fis = null;
        // 对象输入流
        ObjectInputStream ois = null;
        // 输出流
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(zipFile);
            ois = new ObjectInputStream(fis);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>)ois.readObject();
            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 写入到目标文件
            fos = new FileOutputStream(dstFile);
            // 写数据
            fos.write(bytes);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param srcFile 要压缩的文件的全路径
     * @param dstFile 压缩到哪个目录
     */
    public static void zipFile(String srcFile, String dstFile) {
        // 创建文件输入流
        FileInputStream fis = null;
        // 创建文件输出流
        FileOutputStream fos = null;
        // 创建对象输出流
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            // 创建一个和源文件一个大小的数组
            byte[] b = new byte[fis.available()];
            // 读取文件
            fis.read(b);
            // 对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);

            fos = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutPutStream
            oos = new ObjectOutputStream(fos);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 这里我们以对象流的方式写入赫夫曼的编码，是为了以后我们恢复源文件时使用
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                fos.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    // 解压

    /**
     * @param huffmanCodes 赫夫曼编码map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 先得到huffmanBytes对应的二进制的字符串，形式为1010100110...
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换，反向查找
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建一个集合，存放byte
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            // 得到一个对应编码时计数
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                // 递增的取出key
                // i不动，让count移动，直到匹配到一个字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        // 退出for循环后，我们的list中就存放了所有的字符 "i like like ..."
        // 把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转为一个二进制字符串
     *
     * @param flag 表示是否需要补高位，如果是true，表示需要补高位，反之不需要,如果是最后一个字节，无需补高位
     * @param b    byte数
     * @return 是该b对应的二进制的字符串，注意是按补码返回
     */
    private static String byteToBitString(boolean flag, byte b) {
        // byte转为int
        int temp = b;
        // 如果是正数，我们还需要补高位
        if (flag) {
            // 按位与 256， 1 0000 0000 | 0000 0001  =>  1 0000 0001
            temp |= 256;
        }
        // 返回的是temp对应的补码
        String str = Integer.toBinaryString(temp);
        if (flag) {
            // 截取后八位
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    // 封装方法
    private static byte[] huffmanZip(byte[] contentBytes) {
        // 根据byte数组创建节点集合
        List<Node> nodes = getNodes(contentBytes);
        // 根据node节点创建赫夫曼树，得到根节点
        Node root = createHuffmanTree(nodes);
        // 传入根节点得到赫夫曼编码Map
        Map<Byte, String> huffmanCode = getCodes(root);
        // 传入byte数组和赫夫曼编码Map得到压缩后的byte数组
        return zip(contentBytes, huffmanCode);
    }

    // 接收一个字节数组，返回List<Node>集合
    private static List<Node> getNodes(byte[] bytes) {
        // 先创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        // 遍历bytes，统计每个byte出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把每一个键值对转成一个Node对象，并加入到nodes集合
        // 遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 可以通过List创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到大
            // 取出前两个最小的二叉树
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            // 创建一个新的二叉树
            Node parent = new Node(left.weight + right.weight, left, right);
            // 移除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            // 加入新的二叉树
            nodes.add(parent);
        }
        // 返回根节点root
        return nodes.get(0);
    }

    // 生成赫夫曼树对应的赫夫曼编码
    // 用Map<Byte,String>存放，如 32 - 01   97 - 100  ...
    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 在生成赫夫曼编码时，需要去拼接，用StringBuilder存储某个叶子节点的路径

    /**
     * 将传入的code节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node 传入节点
     * @param code 左子节点是0，右子节点是1
     * @param path 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder path) {
        StringBuilder builder = new StringBuilder(path);
        builder.append(code);
        if (node != null) {
            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) {
                // 是非叶子节点
                // 递归处理
                getCodes(node.left, "0", builder);
                // 向右递归
                getCodes(node.right, "1", builder);
            } else {
                // 是叶子节点
                // 找到了某个叶子节点的最后
                huffmanCodes.put(node.data, builder.toString());
            }
        }
    }

    // 根据node节点创建一个赫夫曼编码
    private static Map<Byte, String> getCodes(Node node) {
        if (node == null) {
            return null;
        }
        getCodes(node, "", new StringBuilder());
        return huffmanCodes;
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
     *
     * @param bytes        原始字符串对应的数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]
     * 如：字符串"1010100010101010000100101000100100110110010...." => 对应的byte[] huffmanCodeBytes
     * 8位对应一个byte，放入到huffmanCodeBytes中：
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000 - 1 = 10100111(反码) => 11011000 =  -88]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        // 将字符串转为byte数组
        // 长度
        // int len = stringBuilder.length() % 8 == 0 ? stringBuilder.length() / 8 : stringBuilder.length() / 8 + 1;
        int len = (stringBuilder.length() + 7) / 8;
        // 创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int j = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[j] = (byte)Integer.parseInt(strByte, 2);
            j++;
        }
        return huffmanCodeBytes;
    }
}

// 创建Node，带数据和权值
class Node implements Comparable<Node> {
    // 存放数据本身，比如‘a’ => 97 ,  ' ' => 32
    Byte data;
    // 权值，表示字符出现的次数
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(int weight, Node left, Node right) {
        this.data = null;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
