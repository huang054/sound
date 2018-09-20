package com.sound.config;


import java.util.concurrent.Future;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sound.model.UserTemporary;
import com.sound.vo.Test;
/**
 * Created by fangzhipeng on 2017/4/19.
 */
@Service
public class GitHubLookupService {

    private static final Logger logger = LoggerFactory.getLogger(GitHubLookupService.class);

    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

   @Async
    public Future<String> findUser(String url,MultiValueMap<String, Object> paramMap) throws InterruptedException {
        logger.info("Looking up " + url);
       
        String results = restTemplate.postForObject(url,paramMap, String.class);
        // Artificial delay of 1s for demonstration purposes
        Thread.sleep(1000L);
        return new AsyncResult<>(results);
    }

}