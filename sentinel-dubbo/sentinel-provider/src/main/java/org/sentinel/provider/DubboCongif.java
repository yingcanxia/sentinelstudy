package org.sentinel.provider;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@DubboComponentScan("org.sentinel")
public class DubboCongif {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig=new ApplicationConfig();
		applicationConfig.setName("sentinel-dubbo");
		applicationConfig.setOrganization("cn.shadow");
		return applicationConfig;
	}
	
	@Bean
	public RegistryConfig registoryConfig() {
		RegistryConfig registryConfig=new RegistryConfig();
		registryConfig.setAddress("zookeeper://192.168.1.128:2181");
		
		return registryConfig;
	}
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig=new ProtocolConfig();
		protocolConfig.setName("dubbo");
		protocolConfig.setPort(20880);
		return null;
	}
}
