package com.company;


import java.util.Arrays;
import java.util.List;
import  java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;



public class DemoStream2 {

    public static void main(String args[]){
        Stream.iterate(1,count->count+1).filter(num->num%3==0).limit(6).forEach(System.out::println);

        List<String> alphabets = Arrays.asList("A","B","C","D");
        List<String> names = Arrays.asList("Ana","Blanca","Carmen","Diana");

        Stream<String> opstream=Stream.concat(alphabets.stream(),names.stream());
        opstream.forEach(System.out::println);

        Predicate<Student> p1= new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                    return student.stuName.startsWith("S");
            }
        };

        Predicate<Student> p2=student -> student.stuAge<28 && student.stuName.startsWith("Z");

        Predicate<Student> p3=student -> student.stuAge<100;;

        List<Student> studentList= getStudents();


        boolean bol1=studentList.stream().anyMatch(p1);
        System.out.println(bol1);;
        boolean bol2=studentList.stream().anyMatch(p2);
        System.out.println(bol2);;

        boolean bol3=studentList.stream().noneMatch(p1);
        System.out.println(bol3);;
        boolean bol4=studentList.stream().noneMatch(p2);
        System.out.println(bol4);;

        boolean bol5=studentList.stream().allMatch(p3);
        System.out.println(bol5);;

    }

    public static List<Student> getStudents(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(11, 28, "Lucy"));
        list.add(new Student(28, 27, "Kiku"));
        list.add(new Student(32, 30, "Dani"));
        list.add(new Student(49, 27, "Steve"));
        return list;
    }
}

class Student {
    int stuId;
    int stuAge;
    String stuName;

    Student(int id, int age, String name) {
        this.stuId = id;
        this.stuAge = age;
        this.stuName = name;
    }

    public int getStuId() {
        return stuId;
    }

    public int getStuAge() {
        return stuAge;
    }

    public String getStuName() {
        return stuName;
    }
}
