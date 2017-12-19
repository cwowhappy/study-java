package cwowhappy.study.generics.reflection;


import java.util.List;

public class Container {
    private List<Component<String>> components;


    public List<Component<String>> getComponents() {
        return components;
    }

    public void setComponents(List<Component<String>> components) {
        this.components = components;
    }
}
