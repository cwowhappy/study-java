package cwowhappy.study.reflection;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * 学习获取Field对应的setter和getter方法
 * @author cwowhappy
 * 2017-02-22 Wednesday
 */
public class Sample02 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample02.class);

    public void testSettersAndGetters() {
        Person person = new Person("cwowhappy", LocalDate.of(1990, 1, 1));
        Class<Person> personClass = Person.class;
        String fieldValues = Arrays.stream(personClass.getDeclaredFields()).map(Field::getName)
                .map(this::getGetterMethodNameByFieldName)
                .map(getMethodName -> {
                    Method getMethod = null;
                    try {
                        getMethod = personClass.getMethod(getMethodName);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return getMethod;
                }).filter(getMethod -> null != getMethod).map(getMethod -> {
                    String value = null;
                    try {
                        Object valueObject = getMethod.invoke(person);
                        value = (null == valueObject) ? null : valueObject.toString();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return value;
                }).filter(value -> null != value).reduce((values, value) -> values + "," + value).orElse("");
        LOGGER.info(fieldValues);
    }

    private String getSetterMethodNameByFieldName(String fieldName) {
        String setterMethodName = null;
        if(!Strings.isNullOrEmpty(fieldName)) {
            setterMethodName = String.format("set%s%s", fieldName.substring(0, 1).toUpperCase(), fieldName.substring(1));
        }
        return setterMethodName;
    }

    private String getGetterMethodNameByFieldName(String fieldName) {
        String getterMethodName = null;
        if(!Strings.isNullOrEmpty(fieldName)) {
            getterMethodName = String.format("get%s%s", fieldName.substring(0, 1).toUpperCase(), fieldName.substring(1));
        }
        return getterMethodName;
    }

    public static void main(String[] args) {
        Sample02 sample02 = new Sample02();
        sample02.testSettersAndGetters();
    }
}
