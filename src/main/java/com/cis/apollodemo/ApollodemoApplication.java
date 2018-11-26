package com.cis.apollodemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApollodemoApplication {

    static {
        System.setProperty("-Dapp.id","mr");

    }

    @Autowired
    private  ApplicationConfigBean applicationConfigBean;

    @RequestMapping("/")
    public String show(){
        return "hello world: ConnectionString: " + applicationConfigBean.getConnectionString() + " TimeOut:" + applicationConfigBean.getTimeOut();

    }

    public static void main(String[] args) {
        SpringApplication.run(ApollodemoApplication.class, args);
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello world ";
    }
}
