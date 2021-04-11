package com.luo.payment8003.config;


import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

public class CustomizeAutoConfigurationImportFilter implements AutoConfigurationImportFilter, EnvironmentAware {

    private Environment environment;

    @Override
    public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
        String registryType = environment.getProperty("registry.type", "zookeeper");
        boolean match[] = new boolean[autoConfigurationClasses.length];
        if (registryType.equals("zookeeper")) {
            for (int i = 0; i < autoConfigurationClasses.length; i++) {
                if (!StringUtils.isEmpty(autoConfigurationClasses[i]) &&
                        autoConfigurationClasses[i].equals("com.alibaba.cloud.nacos.registry.NacosServiceRegistryAutoConfiguration")) {
                    match[i] = false;
                } else {
                    match[i] = true;
                }
            }
        } else {
            for (int i = 0; i < autoConfigurationClasses.length; i++) {
                if (!StringUtils.isEmpty(autoConfigurationClasses[i]) &&
                        autoConfigurationClasses[i].equals("org.springframework.cloud.zookeeper.serviceregistry.ZookeeperAutoServiceRegistrationAutoConfiguration")) {
                    match[i] = false;
                } else {
                    match[i] = true;
                }
            }
        }
        return match;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
