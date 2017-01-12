package cwowhappy.study.thread.ch02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cwowhappy
 * 2016-12-29 Thursday
 */
public class Animal {
    private static final Logger LOGGER = LoggerFactory.getLogger(Animal.class);
    protected String name;

    public Animal(String name) {
        this.setName(name);
    }

    public void setName(String name) {
        LOGGER.info("Function[Animal.setName] is invoked");
        this.name = name;
    }

    public synchronized void run(Direction direction) throws InterruptedException {
        LOGGER.info("Animal[{}] begin running toward {}", name, direction);
        Thread.currentThread().sleep(2000);
        LOGGER.info("Animal[{}] finish running toward {}", name, direction);
    }

    public enum Direction {
        WEST(1), EAST(2), NORTH(4), SOUTH(8);
        Direction(int value) {
            this.value = value;
        }
        private int value;
    }
}
