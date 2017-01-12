package cwowhappy.study.thread.ch02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cwowhappy
 * 2016-12-29 Thursday
 */
public class Dog extends Animal {
    private static final Logger LOGGER = LoggerFactory.getLogger(Dog.class);

    public Dog(String name) {
        super(name);
    }

    public void setName(String name) {
        LOGGER.info("Function[Dog.setName] is invoked");
        this.name = name;
    }

    public synchronized void run(Direction direction) throws InterruptedException {
        LOGGER.info("Dog[{}] begin running toward {}", name, direction);
        Thread.currentThread().sleep(200);
        super.run(direction);
//        Thread.currentThread().sleep(2000);
        LOGGER.info("Dog[{}] finish running toward {}", name, direction);
    }

    public void selfRun(Direction direction) throws InterruptedException {
        LOGGER.info("Dog[{}] begin running toward {}", name, direction);
        this.run(direction);
        LOGGER.info("Dog[{}] finish running toward {}", name, direction);
    }

    public void superRun(Direction direction) throws InterruptedException {
        LOGGER.info("Animal[{}] begin running toward {}", name, direction);
        super.run(direction);
        LOGGER.info("Animal[{}] finish running toward {}", name, direction);
    }
}
