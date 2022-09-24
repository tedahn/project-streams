package com.tedahn.projectstreams.producer.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import reactor.kafka.receiver.KafkaReceiver

private var logger: Logger = LoggerFactory.getLogger("SalesPublisher")

@Configuration
class MappingHandlerConfig (
    val kafkaReceiver: KafkaReceiver<String, String>
){

    @Bean
    fun handlerMapping(): HandlerMapping {
        val map = mapOf("/sales-events" to
                WebSocketHandler { session ->
                    session.send(kafkaReceiver.receive().map {
                        it.value()
                    }.map(session::textMessage))
                })
        logger.info("Creating one socket")
        return SimpleUrlHandlerMapping(map, -1)
    }
}