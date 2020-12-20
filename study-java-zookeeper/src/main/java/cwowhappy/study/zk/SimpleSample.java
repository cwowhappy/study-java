package cwowhappy.study.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleSample {
    private static final Logger log = LoggerFactory.getLogger(SimpleSample.class);

    public static void main(String[] args) {
        String zkConnectionString = "127.0.0.1:2181;127.0.0.1:2182;127.0.0.1:2183";
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkConnectionString, new RetryNTimes(10, 5000));
        client.start();
        try {
            client.getChildren().forPath("/").forEach(log::info);
        } catch (Exception e) {
            log.error("Exception:", e);
        }
        client.close();
    }
}
