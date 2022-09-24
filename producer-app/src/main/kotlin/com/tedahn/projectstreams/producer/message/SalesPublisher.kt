package com.tedahn.projectstreams.producer.message

import com.tedahn.projectstreams.common.model.ProductSales
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private var logger: Logger = LoggerFactory.getLogger("SalesPublisher")

@Service
class SalesPublisher (
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${app.kafka.topic-name}")
    private val topicName: String
){

    fun sendMessage(event: ProductSales) {
        kafkaTemplate.send(topicName, event.toString())
        logger.debug("Published Sales: $event")
    }
}