package com.ameya.servicediscovery.client;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ZKClientImpl implements ZKClient {

    private static ZooKeeper zooKeeper;
    private static ZKConnection zkConnection;

    public ZKClientImpl(String host, int port) throws IOException, InterruptedException {
        initialize(host, port);
    }

    private void initialize(String host, int port) throws IOException, InterruptedException {
        zkConnection = new ZKConnection();
        zooKeeper = zkConnection.connect(host, port);
    }

    public void close() throws InterruptedException {
        zkConnection.close();
    }

    @Override
    public void create(String path, byte[] data) throws KeeperException, InterruptedException {
        zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Override
    public boolean exists(String path, boolean watchFlag) throws KeeperException, InterruptedException {
        return zooKeeper.exists(path, watchFlag)!=null;
    }

    @Override
    public String getZNodeData(String path, boolean watchFlag) {
        byte[] zNodeData = null;
        try {
            zNodeData = zooKeeper.getData(path, null, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new String(zNodeData, StandardCharsets.UTF_8);
    }

    @Override
    public void update(String path, byte[] data) throws KeeperException, InterruptedException {
        int version = zooKeeper.exists(path, true).getVersion();
        zooKeeper.setData(path, data, version);
    }

    @Override
    public void delete(String path) throws KeeperException, InterruptedException {
        int version = zooKeeper.exists(path, true).getVersion();
        zooKeeper.delete(path, version);
    }
}
