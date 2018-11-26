package com.cis.apollodemo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig
public class ApplicationConfig {

    @Bean
    public  ApplicationConfigBean applicationConfigBean(){
        return  new ApplicationConfigBean();
    }
}
