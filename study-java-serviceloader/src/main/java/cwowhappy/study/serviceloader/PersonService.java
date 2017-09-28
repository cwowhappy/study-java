package cwowhappy.study.serviceloader;

/**
 * Created by cwowhappy on 2017/6/28.
 */
public class PersonService implements IService {
    @Override
    public String sayHello() {
        return "hello person";
    }

    @Override
    public String getScheme() {
        return "person";
    }
}
