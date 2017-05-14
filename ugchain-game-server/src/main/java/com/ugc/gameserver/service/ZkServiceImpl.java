package com.ugc.gameserver.service;

import com.renren.zookeeper.Accessor;
import com.renren.zookeeper.ZkConfig;
import com.renren.zookeeper.accessor.Subscribe;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fanjl on 2017/4/28.
 */
public class ZkServiceImpl extends Subscribe {

    private volatile static ZkServiceImpl instance;

    private static Accessor accessor = null;


    private static Logger logger = LoggerFactory.getLogger(ZkServiceImpl.class);

    private volatile Map<String, String> hashData = new HashMap<String, String>();

    public static ZkServiceImpl getInstance() {
        if (instance == null) {
            synchronized (ZkServiceImpl.class) {
                if (instance == null) {
                    instance = new ZkServiceImpl("", "", "");
                }
            }
        }
        return instance;
    }

    private static boolean init(ZkConfig zkConfig) {
        try {
            accessor = Accessor.getInstance(zkConfig);
            try {
                accessor.subscribeService(getInstance());
                return true;
            } catch (OperationNotSupportedException e) {
                logger.error("", e);
            } catch (KeeperException e) {
                logger.error("", e);
            } catch (InterruptedException e) {
                logger.error("", e);
            } catch (IOException e) {
                logger.error("", e);
            } catch (Exception e) {
                logger.error("", e);
            }
        } catch (InterruptedException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        }
        return false;
    }

    private ZkServiceImpl(String serviceId, String version, String sharding) {
        super(serviceId, version, sharding);
    }

    @Override
    public void childChanged(List<String> originList,
                             List<String> increaceList, List<String> decreaceList) {
        String parent = getFullPath() + "/";
        for(String node : increaceList){
            try {
                byte[] data = accessor.getContent(parent+node);
                contentChanged(parent + node, null, data);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void contentChanged(String path, byte[] oldValue,byte[] newValue) {
        String[] nodes = path.split("/");
        String leafNode = nodes[nodes.length-1];
        if(newValue == null){
            logger.info("content changed new value == null");
            return;
        }
        String value = new String(newValue);
        logger.info("zookeeper notify " + getFullPath() + " -> " + leafNode + " newValue : " + value);
        update(leafNode,value);

    }
    private void update(String key,String value){
        Map<String, String> tmpHashData = new HashMap<String,String>();
        for (String str : hashData.keySet()) {
            tmpHashData.put(str, hashData.get(str));
        }
        tmpHashData.put(key,value);
        hashData = tmpHashData;
    }
    public Map<String,String> getAllData(){
        return hashData;
    }

}
