package com.huya.springcloud.controller;

import com.huya.springcloud.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class WebController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @RequestMapping("/web/hello")
    public String hello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://SPRINGCLOUD-SERVICE-PROVIDER/service/hello", String.class);
        HttpStatus code = responseEntity.getStatusCode();
        if (code == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return "err";
        }
    }

    @RequestMapping("/web/userById")
    public User user() {
        Map<String, String> map = new HashMap<>(2);
        map.put("id", "2");
        map.put("name", "test");
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://SPRINGCLOUD-SERVICE-PROVIDER/service/userById?id={id}&name={name}", User.class, map);
        HttpStatus code = responseEntity.getStatusCode();
        if (code == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }

    @RequestMapping("/web/addUser")
    public User addUser() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>(2);
        multiValueMap.add("id", "2");
        multiValueMap.add("name", "test");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://SPRINGCLOUD-SERVICE-PROVIDER/service/addUser", multiValueMap, User.class);
        HttpStatus code = responseEntity.getStatusCode();
        if (code == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null;
        }
    }


    @RequestMapping("/web/helloHytrix")
    @HystrixCommand(fallbackMethod = "hystrixError", ignoreExceptions = RuntimeException.class, commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")})
    public String helloHytrix() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://SPRINGCLOUD-SERVICE-PROVIDER/service/hello", String.class);
        HttpStatus code = responseEntity.getStatusCode();
        if (code == HttpStatus.OK) {
            String config = environment.getProperty("config");
            String body = responseEntity.getBody();
            return config + body;
        } else {
            return "err";
        }
    }

    public String hystrixError(Throwable throwable) {
        if (!"".equals(throwable.getMessage())) {
            return throwable.getMessage();
        }
        return "HystrixError";
    }

}
