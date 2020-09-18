package com.svetikov.websockettest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.logging.Logger;

@SpringBootTest
@EnableScheduling
class WebsocketTestApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    @Scheduled(initialDelay = 1000)
    public void scheduledTest(){
        System.out.println("test");
    }

}
