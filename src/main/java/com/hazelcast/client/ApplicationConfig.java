package com.hazelcast.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class ApplicationConfig {

	@Bean
	public ClientConfig clientConfig() {
		
		ClientConfig config = new ClientConfig();
	    config.getNetworkConfig().getKubernetesConfig().setEnabled(true);
	    return config;		
	}
	
	/**
	 * <P>Temporary. Spring Boot should soon autoconfigure
	 * the {@code HazelcastInstance} bean.
	 * </P>
	 */
	@Configuration
	@ConditionalOnMissingBean(HazelcastInstance.class)
	static class HazelcastClientConfiguration {
		
		@Bean
		public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
			return HazelcastClient.newHazelcastClient(clientConfig);
		}
	}
	
}
