package com.tedahn.projectstreams.producer.customers

import com.tedahn.projectstreams.common.model.ProductSales
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class YogurtBuyingCustomer(): DigitalCustomer {

    override fun pickProductSales(): ProductSales {
        return ProductSales("Yogurt", 1.99, Random.nextInt(10))
    }
}