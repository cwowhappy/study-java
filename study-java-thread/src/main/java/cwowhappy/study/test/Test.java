package cwowhappy.study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cwowhappy on 2017/6/23.
 */
public class Test {
    public static void main1(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        Class<ProcessA> processAClass = ProcessA.class;
//        Field field = processAClass.getDeclaredField("name");
//        field.setAccessible(true);
//        field.set(null, "cwowhappy");
//
//        String[] arguments = new String[] {"hello"};
//
//        System.out.println(args.getClass());
//        processAClass.getDeclaredMethod("main", new Class[]{args.getClass()}).invoke(null, new Object[] {args});
//        System.out.println(processAClass.getDeclaredMethod("main", new Class[]{args.getClass()}));
//        System.out.println(field.get(null));
        List<String> strList = Arrays.asList("a", "b", "c");
        System.out.println(strList.stream().reduce((root, item) -> root + "," + item).orElse(""));
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(""))) {
            Files.readLines(new File(""), Charset.defaultCharset());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws JsonProcessingException {
        List<String> taskCodes = Arrays.asList("1", "2");
        Map<Boolean, List<Pair<String, String>>> partitionMap = taskCodes.stream().map(taskCode ->
                new Pair<>(taskCode, "")).collect(Collectors.partitioningBy(pair -> Objects.nonNull(pair.getValue())));
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(partitionMap));
    }

    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair() {}

        public Pair(K key, V value) {
            setKey(key);
            setValue(value);
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
