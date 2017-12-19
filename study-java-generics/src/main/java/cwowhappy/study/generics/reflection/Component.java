package cwowhappy.study.generics.reflection;

public class Component<T> {
    private T content;

    public Component(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }
}
