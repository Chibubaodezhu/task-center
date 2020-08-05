package com.huangjiang.taskcenter.security;

import com.huangjiang.taskcenter.security.impl.rsa.RSACypherManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class CypherManagerConfig {
    Logger logger = LoggerFactory.getLogger(CypherManagerConfig.class);

    @Value(value="classpath:security/rsa/rsa_private_key_pkcs8.pem")
    private Resource privatePemKeyResource;
    @Value(value="classpath:security/rsa/rsa_public_key.pem")
    private Resource publicPemKeyResource;

    @Bean("rsaCypher")
    CypherManager rsaCypher(){
        RSACypherManagerImpl cypherManager = new RSACypherManagerImpl();
        cypherManager.setPrivatePemKeyResource(privatePemKeyResource);
        cypherManager.setPublicPemKeyResource(publicPemKeyResource);
        return cypherManager;
    }

}
