package cwowhappy.study.generics;

public class Dog extends Animal implements Greeting {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void greet() {
        System.out.println("Hello, I'm dog " + name);
    }
}
