package cwowhappy.study.generics;

public class Cat extends Animal implements Greeting {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void greet() {
        System.out.println("Hello, I'm cat " + name);
    }
}
