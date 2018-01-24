package com.siemens.hazelcast.client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.siemens.scr.tde.model.uml.TdeModel;

public class HazelCastrClient {
	
	public static void main(String[] args) {
		
		ClientConfig clientConfig = new ClientConfig();
        clientConfig.addAddress("127.0.0.1:5701");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap map = client.getMap("models");
        
        System.out.println("Map Size:" + map.size());
        map.put(4, new TdeModel());
        System.out.println("Map Size:" + map.size());
		
	}

}
