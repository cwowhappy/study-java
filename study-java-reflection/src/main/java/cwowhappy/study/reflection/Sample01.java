package cwowhappy.study.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author cwowhappy
 * 2017-02-21 Tuesday
 */
public class Sample01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample01.class);

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class<Person> personClass = Person.class;
        Constructor[] personConstructors = personClass.getConstructors();
        Arrays.stream(personConstructors).forEach(personConstructor -> LOGGER.info("{}({})",
                personConstructor.getName(), Arrays.stream(personConstructor.getParameterTypes())
                        .map(Class::getName)
                        .reduce((parameterTypesStr, parameterType) -> parameterTypesStr + "," + parameterType).orElse("")));

        Class<Student> studentClass = Student.class;
        //Class实例的getFields方法只返回声明为public的Field
        LOGGER.info("Student Fields :{}", Arrays.stream(studentClass.getFields())
                .map(Field::getName).reduce((fieldNames, fieldName) -> fieldNames + "," + fieldName).orElse(""));
        //Class实例的getDeclaredFields方法返回类自身声明的所有Field(不包含从父类继承的Field)
        LOGGER.info("Student Declared Fields :{}", Arrays.stream(studentClass.getDeclaredFields())
                .map(Field::getName).reduce((fieldNames, fieldName) -> fieldNames + "," + fieldName).orElse(""));
    }
}
