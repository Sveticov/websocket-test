package com.svetikov.websockettest.service;

import com.svetikov.websockettest.model.Event;
import reactor.core.publisher.Flux;

public interface EventUnicastService {
    void onNext(Event next);
    Flux<Event> getMessage();
}
