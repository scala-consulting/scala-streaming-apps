package me.saksonov.scalaconsulting.generics.c_variance.a_variancepositions.a_javaarr;

import me.saksonov.scalaconsulting.generics.c_variance.a_variancepositions.b_scalaarr.Person;
import me.saksonov.scalaconsulting.generics.c_variance.a_variancepositions.b_scalaarr.Student;

public class JavaArraysExample {

    public static void main(String[] args) {

        Student[] students = new Student[10];

        Person[] persons = students;

        persons[0] = new Person();

        Student student = students[0];

        System.out.println(student);

//        Person[] persons = new Person[10];
    }
}
