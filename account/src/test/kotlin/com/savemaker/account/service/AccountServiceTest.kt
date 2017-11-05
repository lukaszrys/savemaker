package com.savemaker.account.service

import com.savemaker.account.AccountEntryData
import com.savemaker.account.domain.Account
import com.savemaker.account.domain.Currency
import com.savemaker.account.domain.Wallet
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.domain.dto.AppUserDto
import com.savemaker.account.event.AccountRegisterEventPublisher
import com.savemaker.account.service.exception.AccountAlreadyExistException
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
class AccountServiceTest : AccountEntryData{
    @Mock
    lateinit var accountRepository: AccountRepository

    @Mock
    lateinit var accountRegisterEventPublisher : AccountRegisterEventPublisher

    lateinit var tested: AccountService

    @Before
    fun setupMock(){
        tested = AccountServiceImpl(accountRepository, accountRegisterEventPublisher)
    }

    @Test
    fun shouldRegisterUser() {
        //Given
        val user = AppUserDto("email@email.pl","password")
        val account = defaultAccount(user.username)
        Mockito.`when`(accountRepository.findByEmail(user.username)).thenReturn(Optional.empty())
        Mockito.`when`(accountRepository.save(Mockito.any(Account::class.java))).thenReturn(account)
        //When
        tested.register(user)
        //Then
        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any(Account::class.java))
        Mockito.verify(accountRepository, Mockito.times(1)).findByEmail(user.username)
        Mockito.verify(accountRegisterEventPublisher, Mockito.times(1)).prepareAndPublishEvent(user)
    }

    @Test(expected = AccountAlreadyExistException::class)
    fun shouldRegisterThrowException() {
        //Given
        val user = AppUserDto("email@email.pl","password")
        val account = defaultAccount(user.username)
        Mockito.`when`(accountRepository.findByEmail(user.username)).thenReturn(Optional.of(account))
        //When
        tested.register(user)
        //Then exception is thrown
    }

    @Test
    fun shouldUpdateUser(){
        // Given
        val username = "email@email.pl"
        val account = Account(username, 0, "n/a", Wallet(BigDecimal.ZERO, BigDecimal.ZERO, Currency.PLN))
        val accountToUpdate = Account(username, 26, "developer", Wallet(BigDecimal.valueOf(100), BigDecimal.ZERO, Currency.EUR))
        Mockito.`when`(accountRepository.findByEmail(username)).thenReturn(Optional.of(account))
        Mockito.`when`(accountRepository.save(Mockito.any(Account::class.java))).thenReturn(accountToUpdate)
        // When
        tested.update(accountToUpdate, username)
        // Then
        Mockito.verify(accountRepository, Mockito.times(1)).findByEmail(username)
        Mockito.verify(accountRepository, Mockito.times(1)).save(Mockito.any(Account::class.java))
        Assert.assertEquals(accountToUpdate.profession, account.profession)
    }
}