package com.my.java.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author Chen Yaqi
 * @version 1.0
 */
public class CollectionTest {
    @Test
    public void test1() {
        Collection collection = new ArrayList();
        // add()添加
        collection.add("AA");
        collection.add("BB");
        collection.add(true);
        collection.add(1223);
        // size()获取元素个数
        System.out.println(collection.size());
        // addAll()把另一个集合里的全部元素添加进去
        Collection collection1 = new ArrayList();
        collection1.add(111);
        collection.add("adad");
        collection.addAll(collection1);
        // clear()清空集合里的元素
        collection.clear();
        // isEmpty()判断当前集合是否为空.不等于是否为null
        System.out.println(collection.isEmpty());
    }

    @Test
    public void test2() {
        // contains()判断集合内是否包含这个元素，注意，用equals()
        Collection<String> coll = new ArrayList<>();
        coll.add(new String("abc"));
        coll.add("SSS");
        coll.add("aAAA");
        // String类里重写了equals()，若是自定义类，则需要重写equals
        System.out.println(coll.contains(new String("abc")));
        // containsAll(Collection coll1) 判断coll1中的所有元素是否都在当前集合中
        String[] strings = coll.toArray(new String[0]);
        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void test3() {
        Collection<Object> coll = new ArrayList<>();
        coll.add(123);
        coll.add("ABC");
        coll.add(true);
        coll.add(789);

        // coll.iterator()方法生成一个迭代器对象
        Iterator<Object> iterator = coll.iterator();
        // 每次next()前都要先调用hasNext()检查是否存在元素
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            // 如果存在“ABC”，则从集合中移除此元素
            if ("ABC".equals(obj)) {
                iterator.remove();
            }
            System.out.println(obj);
        }

        Iterator<Object> iterator1 = coll.iterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.next());
        }

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(2); // index
        list.remove(new Integer(2));  // value
        System.out.println(list);
    }

    @Test
    public void test4() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(99);
        treeSet.add(-29);
        treeSet.add(15);
        treeSet.add(8);
        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test5() {

        Comparator<User> com = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getName().equals(o2.getName())){
                    return Integer.compare(o1.getAge(),o2.getAge());
                } else {
                    return o1.getName().compareTo(o2.getName());
                }
            }
        };

        TreeSet<User> treeSet = new TreeSet<>(com);
        treeSet.add(new User("May", 28));
        treeSet.add(new User("ABC", 16));
        treeSet.add(new User("YUE", 15));
        treeSet.add(new User("Jone", 20));
        treeSet.add(new User("Jone", 21));
        Iterator<User> iterator = treeSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test(){
        HashSet set = new HashSet();
        Person1 p1 = new Person1(1001, "AA");
        Person1 p2 = new Person1(1002, "BB");

        set.add(p1);
        set.add(p2);

        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);
        set.add(new Person1(1001, "CC"));
        System.out.println(set);
        set.add(new Person1(1001, "AA"));
        System.out.println(set);
    }
}

class Person1{
    int id;
    String name;

    public Person1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person1 person1 = (Person1)o;

        if (id != person1.id) return false;
        return name != null ? name.equals(person1.name) : person1.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

class User implements Comparable<User> {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    // 自然排序
    @Override
    public int compareTo(User o) {
        if(this.name.equals(o.name)){
            return Integer.compare(this.age, o.age);
        } else {
            return this.name.compareTo(o.name);
        }
    }
}
