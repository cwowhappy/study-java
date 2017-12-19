package cwowhappy.study.generics.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class Sample {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<Container> containerClass = Container.class;
        Method methodGetComponents = containerClass.getDeclaredMethod("getComponents");
        Type returnTypeGetComponents = methodGetComponents.getGenericReturnType();
        print(returnTypeGetComponents);

        Method methodSetComponents = containerClass.getDeclaredMethod("setComponents", List.class);
        for (Type parameterType :methodSetComponents.getGenericParameterTypes()) {
            print(parameterType);
        }
    }

    public static void print(Type type) {
        System.out.println(type);
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            for (Type typeArgument : parameterizedType.getActualTypeArguments()) {
                print(typeArgument);
            }

        }

    }
}
