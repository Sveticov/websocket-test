package com.svetikov.websockettest.service;

import com.svetikov.websockettest.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class EventGenerator {

    private AtomicInteger count = new AtomicInteger(0);
    private final EventUnicastService eventUnicastService;


    public EventGenerator(EventUnicastService eventUnicastService) {
        this.eventUnicastService = eventUnicastService;
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void generateEvent() {
        int countAndIncrement = count.getAndIncrement();
        Event event = new Event("event", countAndIncrement);
        log.info("event " + event.toString());
        eventUnicastService.onNext(event);
    }
}
