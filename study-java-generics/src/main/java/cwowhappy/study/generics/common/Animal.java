package cwowhappy.study.generics.common;

public class Animal implements Greeting {
    protected String name;

    public Animal(String name) {
        setName(name);
    }

    @Override
    public void greet() {
        System.out.println("Hello, I'm animal " + name);
    }

    public void setName(String name) {
        this.name = name;
    }
}
