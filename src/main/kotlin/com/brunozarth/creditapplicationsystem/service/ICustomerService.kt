package com.brunozarth.creditapplicationsystem.service

import com.brunozarth.creditapplicationsystem.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)
}