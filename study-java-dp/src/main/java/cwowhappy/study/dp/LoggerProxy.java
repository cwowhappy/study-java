package cwowhappy.study.dp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by cwowhappy on 2017/6/8.
 */
public class LoggerProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            System.out.println(String.format("%s [%s] %s", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), method.getName(), args[0]));
            return null;
        }
    }
}
