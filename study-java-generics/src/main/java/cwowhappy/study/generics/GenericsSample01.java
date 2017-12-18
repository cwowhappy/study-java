package cwowhappy.study.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericsSample01 {
    public static void greets(List<Animal> animals) {
        Objects.requireNonNull(animals);
        animals.forEach(Animal::greet);
    }

    public static void greetsInWildcard1(List<? super Animal> animals) {
        Objects.requireNonNull(animals);
        animals.forEach(Object::toString);
    }

    public static <T extends Animal> void greetsInWildcard(List<T> animals) {
        Objects.requireNonNull(animals);
        animals.forEach(Greeting::greet);
    }

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("animal-01"));

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("dog-01"));

        greets(animals);
        greetsInWildcard(dogs);
    }
}
