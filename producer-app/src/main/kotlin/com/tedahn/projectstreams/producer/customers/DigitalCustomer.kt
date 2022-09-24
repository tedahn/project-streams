package com.tedahn.projectstreams.producer.customers

import com.tedahn.projectstreams.common.model.ProductSales
import org.springframework.stereotype.Service

@Service
interface DigitalCustomer {
    fun pickProductSales(): ProductSales
}