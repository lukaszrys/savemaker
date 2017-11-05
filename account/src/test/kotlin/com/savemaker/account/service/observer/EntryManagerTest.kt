package com.savemaker.account.service.observer

import com.savemaker.account.AccountEntryData
import com.savemaker.account.domain.*
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.domain.repository.EntryRepository
import com.savemaker.account.service.AccountServiceImpl
import com.savemaker.account.service.EntryService
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import java.math.BigDecimal
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class EntryManagerTest : AccountEntryData {
    @Mock
    lateinit var accountRepository: AccountRepository

    @Mock
    lateinit var entryRepository: EntryRepository

    lateinit var tested: EntryManager

    lateinit var testedService: EntryService

    @Before
    fun setupMock(){
        tested = EntryManager()
        testedService = EntryService(accountRepository, entryRepository)
    }

    @Test
    fun shouldAddEntryToWalletAndStore() {
        //Given
        val account = defaultAccount("email@email.pl")
        val wallet = account.wallet
        val amount = BigDecimal.valueOf(100)
        val entry = defaultEntry(account, amount)
        Mockito.`when`(accountRepository.findByEmail(account.email)).thenReturn(Optional.of(account))
        tested.addListener(testedService)
        //When
        tested.notifyObservers(entry,account.email)
        //Then
        Mockito.verify(accountRepository, Mockito.times(1)).findByEmail(account.email)
        Mockito.verify(entryRepository, Mockito.times(1)).save(Mockito.any(Entry::class.java))
        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any(Account::class.java))
        Assert.assertEquals(amount, wallet.amount)
    }
}