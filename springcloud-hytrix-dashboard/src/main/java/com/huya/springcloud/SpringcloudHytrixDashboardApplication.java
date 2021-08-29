package com.huya.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class SpringcloudHytrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudHytrixDashboardApplication.class, args);
    }

}
