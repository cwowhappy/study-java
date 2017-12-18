package cwowhappy.study.java.kotlin;

public class TestKotlinInvoke {
    public String getName() {
        return TestKotlinInvoke.class.getName();
    }

    public static void main(String[] args) {
        Person person = new Person("cwowhappy");
        person.say("Hello Kotlin");
    }
}
