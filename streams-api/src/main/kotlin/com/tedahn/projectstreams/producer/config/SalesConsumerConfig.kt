package com.tedahn.projectstreams.producer.config

import com.tedahn.projectstreams.common.model.ProductSales
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
class SalesConsumerConfig (
    private val kafkaProperties: KafkaProperties
){

    @Bean
    fun consumerFactory(): ConsumerFactory<String, ProductSales> {
        val props: MutableMap<String, Any> = kafkaProperties.buildConsumerProperties()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperties.bootstrapServers
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.GROUP_ID_CONFIG] = "json"
        return DefaultKafkaConsumerFactory(props)
    }
}