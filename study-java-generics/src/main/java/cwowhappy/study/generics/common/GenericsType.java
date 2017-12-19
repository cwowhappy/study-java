package cwowhappy.study.generics.common;


public class GenericsType<T extends Greeting> {
    private T instance;

    public GenericsType(T instance) {
        this.instance = instance;
    }

    public void greet() {
        instance.greet();
    }
}
