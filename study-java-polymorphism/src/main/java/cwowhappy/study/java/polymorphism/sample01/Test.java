package cwowhappy.study.java.polymorphism.sample01;

import cwowhappy.study.java.polymorphism.common.Level;
import cwowhappy.study.java.polymorphism.common.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        Animal animal = new Shepherd();
        System.out.println(animal instanceof Dog);
        Dog dog = (Dog) animal;
        System.out.println(dog instanceof Shepherd);

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        Level level = Level.valueOf("LEVEL1");
        switch (level) {
            case LEVEL1:
                break;
            case LEVEL2:
                break;
        }

        Status status = Status.valueOf("FAILED");
        switch (status) {
            case SUCCEEDED:
                break;
            case FAILED:
                break;
        }
    }
}
