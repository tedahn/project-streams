package com.tedahn.projectstreams.producer.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import java.util.*


@Configuration
class SalesConsumerConfig (
    private val kafkaProperties: KafkaProperties,
    @Value("\${app.kafka.topic-name}")
    private val topicName: String
){

    @Bean
    fun kafkaReceiver(): KafkaReceiver<String, String> {
        val props: MutableMap<String, Any> = kafkaProperties.buildConsumerProperties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.GROUP_ID_CONFIG] = "api-consumer-group"
        return KafkaReceiver.create(ReceiverOptions.create<String?, String?>(props).subscription(Collections.singleton(topicName)))
    }
}