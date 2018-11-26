package com.cis.apollodemo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

public class ApplicationConfigBean {

    @Value("${timeOut:200}")
    private int timeOut;
    private String connectionString;

    @ApolloConfig
    private Config config;

    @ApolloConfig("TEST1.ConfigCenter")
    private  Config configCenterConfig;

    @PostConstruct
    void initialize(){
        System.out.println("timeOut is " + timeOut);
        System.out.println("connectionString is " +connectionString);

        System.out.println("Keys for config" + config.getPropertyNames());

        System.out.println("Keys for configCenterConfig is " +configCenterConfig.getPropertyNames());


    }

    public int getTimeOut() {
        return timeOut;
    }

    public String getConnectionString() {
        return connectionString;
    }

    @Value("${connectionString}")
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }


    private  void someChangeHandler(ConfigChangeEvent changeEvent){
        System.out.println("[someChangeHandler] changes for namespace: " + changeEvent.getNamespace());
        if (changeEvent.isChanged("TimeOut")){
            refreshTimeOut();
        }

        if (changeEvent.isChanged("ConnectionString")){
            setConnectionString(changeEvent.getChange("ConnectionString").getNewValue());
        }

    }

    private  void configCenterChangeHandler(ConfigChangeEvent changeEvent){
        System.out.println("[configCenterChangeHandler] for namespace:" + changeEvent.getNamespace());

        for (String key: changeEvent.changedKeys()){
            ConfigChange change = changeEvent.getChange(key);
            System.out.println("[configCenterChangeHandler] Change Key: "+ change.getPropertyName() +"oldValue:" + change.getOldValue() +" newValue:" + change.getNewValue() );
        }
    }

    private void refreshTimeOut() {
        timeOut = config.getIntProperty("TimeOut", timeOut);

        System.out.println("Refresh timeout is " + timeOut);
    }
}
