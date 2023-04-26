package com.brunozarth.creditapplicationsystem.service

import com.brunozarth.creditapplicationsystem.entity.Credit
import java.util.*

interface ICreditService {
    fun save(credit: Credit): Credit
    fun findAllByCustomer(customerId: Long): List<Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}