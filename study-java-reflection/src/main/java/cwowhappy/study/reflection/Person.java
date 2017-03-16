package cwowhappy.study.reflection;

import java.time.LocalDate;

/**
 * @author cwowhappy
 * 2017-02-21 Tuesday
 */
public class Person {
    private String name;
    private LocalDate birthday;
    private Gender gender;

    public Person() {
        this(null);
    }

    public Person(String name) {
        this(name, null);
    }

    public Person(String name, LocalDate birthday) {
        this(name, birthday, Gender.MALE);
    }

    public Person(String name, LocalDate birthday, Gender gender) {
        setName(name);
        setBirthday(birthday);
        setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public enum Gender{
        MALE, FEMALE
    }
}
