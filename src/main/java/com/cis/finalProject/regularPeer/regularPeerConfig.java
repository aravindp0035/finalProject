package com.cis.finalProject.regularPeer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class regularPeerConfig {

	@Value("${rmi.service.url}")
    private String rmiServiceUrl;

    @Bean
    public RmiProxyFactoryBean rmiProxyFactoryBean() {
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceUrl(rmiServiceUrl);
        rmiProxyFactoryBean.setServiceInterface(BlockchainInterface.class);
        return rmiProxyFactoryBean;
    }
}
