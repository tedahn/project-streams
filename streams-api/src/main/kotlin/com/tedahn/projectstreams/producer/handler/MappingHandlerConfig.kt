package com.tedahn.projectstreams.producer.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import reactor.core.publisher.Sinks

private var logger: Logger = LoggerFactory.getLogger("SalesPublisher")

@Configuration
class MappingHandlerConfig {
    val sink = Sinks.many().multicast().onBackpressureBuffer<String>()

    @KafkaListener(topics = ["\${app.kafka.topic-name}"], groupId = "api-consumer-group")
    fun receiveData(message: String) {
        this.sink.tryEmitNext(message)
    }

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/fromKafka" to
                WebSocketHandler { session ->
                    session.send(this.sink.asFlux().map(session::textMessage))
                })
        logger.info("Creating one socket")
        return SimpleUrlHandlerMapping(map, -1)
    }
}