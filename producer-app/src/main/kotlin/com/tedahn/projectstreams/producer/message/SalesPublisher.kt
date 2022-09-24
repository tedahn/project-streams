package com.tedahn.projectstreams.producer.message

import com.fasterxml.jackson.databind.ObjectMapper
import com.tedahn.projectstreams.common.model.ProductSales
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

private var logger: Logger = LoggerFactory.getLogger("SalesPublisher")
private var objectMapper: ObjectMapper = ObjectMapper()
@Service
class SalesPublisher (
    private val kafkaTemplate: KafkaTemplate<String, String>,
    @Value("\${app.kafka.topic-name}")
    private val topicName: String
){

    fun sendMessage(event: ProductSales) {
        val json = objectMapper.writeValueAsString(event)
        kafkaTemplate.send(topicName, json)
        logger.debug("Published Sales: $json")
    }
}