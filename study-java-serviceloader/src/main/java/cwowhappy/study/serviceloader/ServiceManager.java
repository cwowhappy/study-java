package cwowhappy.study.serviceloader;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cwowhappy on 2017/6/28.
 */
public class ServiceManager {
    private Map<String, IService> serviceMap;

    public ServiceManager() {
        serviceMap = new HashMap<>();
    }

    public void registerService(IService service) {
        serviceMap.put(service.getScheme(), service);
    }

    public String sayHello(String scheme) {
        IService service = serviceMap.get(scheme);
        return (null == service) ? null : service.sayHello();
    }
}
