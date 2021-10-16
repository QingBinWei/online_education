package com.guigu.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.guigu"})
@MapperScan("com.guigu.educms.mapper")
@EnableDiscoveryClient  //微服务注册  nacos
public class EduCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduCmsApplication.class,args);
    }
}
