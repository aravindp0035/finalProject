package com.cis.finalProject.superPeer1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

@Configuration
public class RmiClientConfig {

    @Bean(name = "myRemoteServiceClient")
    public RmiProxyFactoryBean myRemoteService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:1099/Blockchain");
        factoryBean.setServiceInterface(BlockchainInterface.class);
        return factoryBean;
    }
}
