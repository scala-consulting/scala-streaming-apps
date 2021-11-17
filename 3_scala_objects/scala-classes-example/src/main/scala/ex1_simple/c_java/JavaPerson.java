package ex1_simple.c_java;

public class JavaPerson {

    private int age;

    public JavaPerson(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int newAge) {
//        this.age = newAge;
        if (newAge > age) age = newAge;
    }
}
