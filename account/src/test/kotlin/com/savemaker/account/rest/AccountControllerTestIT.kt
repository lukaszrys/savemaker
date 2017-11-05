package com.savemaker.account.rest

import com.netflix.ribbon.proxy.annotation.Http
import com.savemaker.account.AccountEntryData
import com.savemaker.account.IntegrationTestConfig
import com.savemaker.account.domain.Account
import com.savemaker.account.domain.Entry
import com.savemaker.account.domain.repository.AccountRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.math.BigDecimal

class AccountControllerTestIT : IntegrationTestConfig(), AccountEntryData{

    @Autowired
    private lateinit var accountRepository : AccountRepository

    private val account = defaultAccount("email@email.pl")

    @Before
    fun reset() {
        mongoTemplate().dropCollection(Account::class.java)
        mongoTemplate().dropCollection(Entry::class.java)
        //TODO: save using mongoTemplate
        accountRepository.save(account)
    }

    @Test
    fun shouldAddEntryToWalletAndStore() {
        //Given
        val ten = BigDecimal.TEN
        val entry = defaultEntry(account, ten)

        //When
        val response : ResponseEntity<Unit> = testRestTemplate.postForEntity(accountUri() + "/$account.email/entry", entry, Unit::class.java)

        //Then
        Assert.assertEquals(response.statusCode, HttpStatus.OK)
        Assert.assertEquals(accountRepository.findByEmail(account.email).get().wallet.amount, ten)
    }

    private fun accountUri(): String {
        return "http://localhost:$port"
    }
}