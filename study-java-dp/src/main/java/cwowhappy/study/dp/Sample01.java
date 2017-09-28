package cwowhappy.study.dp;

import java.lang.reflect.Proxy;

/**
 * Created by cwowhappy on 2017/6/8.
 */
public class Sample01 {
    public static void main(String[] args) {
        Class<Logger> loggerClass = Logger.class;
        Logger logger = (Logger) Proxy.newProxyInstance(loggerClass.getClassLoader(), new Class[] {loggerClass}, new LoggerProxy());
        logger.info(logger.toString());
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }
}
