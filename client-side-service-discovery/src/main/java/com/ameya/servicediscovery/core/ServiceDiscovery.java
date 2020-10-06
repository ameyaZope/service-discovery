package com.ameya.servicediscovery.core;

import com.ameya.servicediscovery.api.Server;
import com.ameya.servicediscovery.api.Service;
import com.ameya.servicediscovery.client.ZKClient;
import com.ameya.servicediscovery.client.ZKClientImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDiscovery {

    private ZKClient zkClient;
    private ObjectMapper objectMapper;

    public ServiceDiscovery(String host, int port) throws IOException, InterruptedException {
        zkClient = new ZKClientImpl(host, port);
        objectMapper = new ObjectMapper();
    }

    public boolean isRegistered(String serviceName) {
        return true;
    }

    public boolean register(Service service) {
        final String path = getPathFromName(service.getName());
        final List<Server> serverList = service.getServerList();
        try {
            String data = objectMapper.writeValueAsString(serverList);
            if(zkClient.exists(path, false)==false) {
                zkClient.create(path, data.getBytes());
            }
            else {
                String zNodeData = (String) zkClient.getZNodeData(path,false);
                List<Server> currentServers =
                        objectMapper.readValue(zNodeData, new TypeReference<List<Server>>(){});

                currentServers.addAll(service.getServerList());
                String modifiedZNodeData = objectMapper.writeValueAsString(currentServers);
                zkClient.update(path, modifiedZNodeData.getBytes());
            }
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteServices(String path) {
        try {
            zkClient.delete(path);
            return true;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Server> getServers(String serviceName) {
        try {
            String serversString = zkClient
                    .getZNodeData(getPathFromName(serviceName), false);
            return objectMapper.readValue(serversString, new TypeReference<List<Server>>() {});
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<Server>();
    }

    private String getPathFromName(String serviceName) {
        return "/serviceDiscovery/" + serviceName;
    }
}
