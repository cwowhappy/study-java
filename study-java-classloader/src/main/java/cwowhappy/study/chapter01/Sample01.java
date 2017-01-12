package cwowhappy.study.chapter01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author cwowhappy
 */
public class Sample01 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample01.class);

    public void test() {
        ClassLoader classLoader = new URLClassLoader(new URL[]{}, Sample01.class.getClassLoader());
        try {
            classLoader.loadClass("");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }
}
