package cwowhappy.study.serviceloader;

/**
 * Created by cwowhappy on 2017/6/28.
 */
public class AnimalService implements IService {
    @Override
    public String sayHello() {
        return "hello animal";
    }

    @Override
    public String getScheme() {
        return "animal";
    }
}
