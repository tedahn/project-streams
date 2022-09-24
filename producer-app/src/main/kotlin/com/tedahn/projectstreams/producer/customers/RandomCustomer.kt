package com.tedahn.projectstreams.producer.customers

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class RandomCustomer (
    private val list: List<DigitalCustomer>
) {
    fun getCustomer() : DigitalCustomer {
        return list[Random.nextInt(list.size)]
    }
}