package com.siemens.hazelcast.config;

import java.io.FileNotFoundException;
import java.util.Map;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.siemens.scr.tde.model.uml.TdeModel;

public class TedesoHazelCastConfig {

	public static void main(String[] args) throws FileNotFoundException {
		
		Config cfg = new Config();
/*		
		cfg.setInstanceName("tedeso-instance");
		        
		NetworkConfig network = cfg.getNetworkConfig();
		Join join = network.getJoin();
		join.getMulticastConfig().setEnabled(false);
		join.getTcpIpConfig().addMember("10.45.67.32").addMember("10.45.67.100")
		            .setRequiredMember("192.168.10.100").setEnabled(true);
		network.getInterfaces().setEnabled(true).addInterface("10.45.67.*");
		        
		MapConfig mapCfg = new MapConfig();
		mapCfg.setName("testMap");
		mapCfg.setBackupCount(2);
		mapCfg.getMaxSizeConfig().setSize(10000);
		mapCfg.setTimeToLiveSeconds(300);
		        
		MapStoreConfig mapStoreCfg = new MapStoreConfig();
		mapStoreCfg.setClassName("com.hazelcast.examples.DummyStore").setEnabled(true);
		mapCfg.setMapStoreConfig(mapStoreCfg);

		NearCacheConfig nearCacheConfig = new NearCacheConfig();
		nearCacheConfig.setMaxSize(1000).setMaxIdleSeconds(120).setTimeToLiveSeconds(300);
		mapCfg.setNearCacheConfig(nearCacheConfig);

		cfg.addMapConfig(mapCfg);*/
		
		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		Map<String, Object> mapCustomers = instance.getMap("models");
		mapCustomers.put("1", new TdeModel());
		mapCustomers.put("2", new TdeModel());
		mapCustomers.put("3", new TdeModel());

		System.out.println("Customer with key 1: " + mapCustomers.get("1"));
		System.out.println("Map Size:" + mapCustomers.size());

	}

}
