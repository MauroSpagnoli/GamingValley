package Mauro;

import java.util.Collections; 

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;


@EnableHazelcastHttpSession
@EnableCaching
@SpringBootApplication
public class ProyectoDadApplication {
	
	private static final Log LOG = LogFactory.getLog(ProyectoDadApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ProyectoDadApplication.class, args);
	}
	
	@Bean
	public Config config() {
		 Config config = new Config();
		 JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		 joinConfig.getMulticastConfig().setEnabled(false);
		 joinConfig.getTcpIpConfig().setEnabled(true).setMembers(Collections.singletonList("100.112.84.121"));
		 return config;		
	}
	
	@Bean
    public CacheManager cacheManager() {	
    	LOG.info("Activando cache.");	
    	return new ConcurrentMapCacheManager("GamingValley"); 
    }
}
