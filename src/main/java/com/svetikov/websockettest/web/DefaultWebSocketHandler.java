package com.svetikov.websockettest.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.svetikov.websockettest.service.EventUnicastService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@Component
public class DefaultWebSocketHandler implements WebSocketHandler {

    private final EventUnicastService eventUnicastService;
    private final ObjectMapper objectMapper;

    public DefaultWebSocketHandler(EventUnicastService eventUnicastService, ObjectMapper objectMapper) {
        this.eventUnicastService = eventUnicastService;
        this.objectMapper = objectMapper;
    }

//    @Override
//    public List<String> getSubProtocols() {
//        return null;
//    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {

        Flux<WebSocketMessage> messages=session.receive()
                .flatMap(message->{
                    log.info(message.getPayloadAsText());
                    return eventUnicastService.getMessage();
                })
                .flatMap(o->{
                    try {
                        return Mono.just(objectMapper.writeValueAsString(o));
                    } catch (JsonProcessingException e) {
                       return Mono.error(e);
                    }
                })
                .map(session::textMessage);


        return session.send(messages);
    }
}
