package com.example.baitapcyber.config;

import com.example.baitapcyber.Entity.DemoEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoBeanConfig {
    @Bean
    public DemoEntity createDemoEntity(){
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setName("Nguyen Van Thanh");
        demoEntity.setId(1);

        return demoEntity;
    }

}