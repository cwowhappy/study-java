package cwowhappy.study.thread.ch02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cwowhappy
 * 2016-12-29 Thursday
 */
public class Processor02 {
    public static void main(String[] args) throws InterruptedException {
        Dog dog = new Dog("test");
//        Thread selfRunTask = new Thread(new SelfRunTask(dog));
//        Thread superRunTask = new Thread(new SuperRunTask(dog));
//        superRunTask.start();
//        selfRunTask.start();
//        selfRunTask.join();
//        superRunTask.join();
        String[] nameList = new String[2];
        final String[] duplicatedNameList = nameList;
        nameList = null;

        System.out.println(null == duplicatedNameList);
    }

    private static class SelfRunTask implements Runnable {
        private Dog dog;
        public SelfRunTask(Dog dog) {
            this.dog = dog;
        }
        @Override
        public void run() {
            for(int i = 1; i <= 5; i++) {
                try {
                    dog.run(Animal.Direction.EAST);
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
    private static class SuperRunTask implements Runnable {
        private Dog dog;
        public SuperRunTask(Dog dog) {
            this.dog = dog;
        }
        @Override
        public void run() {
            for(int i = 1; i <= 5; i++) {
                try {
                    dog.superRun(Animal.Direction.WEST);
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
            }
        }
    }
}
