package com.brunozarth.creditapplicationsystem.service

import com.brunozarth.creditapplicationsystem.entity.Address
import com.brunozarth.creditapplicationsystem.entity.Credit
import com.brunozarth.creditapplicationsystem.entity.Customer
import com.brunozarth.creditapplicationsystem.exception.BusinessException
import com.brunozarth.creditapplicationsystem.repository.CreditRepository
import com.brunozarth.creditapplicationsystem.repository.CustomerRepository
import com.brunozarth.creditapplicationsystem.service.impl.CreditService
import com.brunozarth.creditapplicationsystem.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

//@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {
    @MockK lateinit var customerRepository: CustomerRepository
    @MockK lateinit var creditRepository: CreditRepository

    @InjectMockKs
    lateinit var customerService: CustomerService
    @InjectMockKs lateinit var creditService: CreditService

    @Test
    fun `should create credit`(){
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildCustomer(id = fakeId)
        val fakeCredit: Credit = buildCredit(customer = fakeCustomer)
        every { customerService.findById(fakeId) } returns fakeCustomer
        every { creditRepository.save(any()) } returns fakeCredit


        //when
        val actual: Credit = creditService.save(fakeCredit)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)
        verify(exactly = 1) { creditRepository.save(fakeCredit) }
    }



    private fun buildCustomer(
        firstName: String = "Bruno",
        lastName: String = "Zarth",
        cpf: String = "47653984692",
        email: String = "bruno@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Main Street",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
        id = id
    )

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: Customer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )
}


