package com.newDataStructures.graphAbout;

import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        ArrayList<Student> stus = new ArrayList<>();
        Student student = new Student("123");
        String[] ids = {"1", "2", "3"};
        for (int i = 0; i < ids.length; i++) {
            student.id = ids[i];
            stus.add(student);
        }
        System.out.println(stus);
    }

    static class Student{
        String id;
        public Student(String id){
            this.id = id;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }
}
