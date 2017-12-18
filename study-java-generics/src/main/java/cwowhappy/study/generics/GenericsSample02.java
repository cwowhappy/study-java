package cwowhappy.study.generics;

import java.util.List;

public class GenericsSample02 {
    public static <T extends Dog> T greet(GenericsType<T> dog) {
        dog.greet();
    }

    public static <T extends Cat> T greet(GenericsType<T> cat) {
        cat.greet();
    }
}
