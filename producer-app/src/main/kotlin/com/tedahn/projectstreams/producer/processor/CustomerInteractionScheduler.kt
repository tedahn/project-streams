package com.tedahn.projectstreams.producer.processor

import com.tedahn.projectstreams.producer.customers.RandomCustomer
import com.tedahn.projectstreams.producer.message.SalesPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CustomerInteractionScheduler (
    private val publisher: SalesPublisher,
    private val randomCustomer: RandomCustomer
){
    @Scheduled(fixedDelay = 1000)
    fun scheduleFixedDelayTask() {
        publisher.sendMessage(randomCustomer.getCustomer().pickProductSales())
    }

}