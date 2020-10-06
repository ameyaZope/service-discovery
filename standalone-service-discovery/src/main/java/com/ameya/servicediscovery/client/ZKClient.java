package com.ameya.servicediscovery.client;

import org.apache.zookeeper.KeeperException;

public interface ZKClient {
    void create(String path, byte[] data) throws KeeperException, InterruptedException;
    boolean exists(String path, boolean watchFlag) throws KeeperException, InterruptedException;
    String getZNodeData(String path, boolean watchFlag) throws KeeperException, InterruptedException;
    void update(String path, byte[] data) throws KeeperException, InterruptedException;
    void delete(String path) throws KeeperException, InterruptedException;
}
