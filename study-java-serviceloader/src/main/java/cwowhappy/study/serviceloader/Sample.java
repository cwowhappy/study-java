package cwowhappy.study.serviceloader;

import java.util.ServiceLoader;

/**
 * Created by cwowhappy on 2017/6/28.
 */
public class Sample {
    public static void main(String[] args) {
        ServiceManager serviceManager = new ServiceManager();
        ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
        for(IService service : serviceLoader) {
            serviceManager.registerService(service);
        }

        System.out.println(serviceManager.sayHello("person"));
        System.out.println(serviceManager.sayHello("animal"));
    }
}
