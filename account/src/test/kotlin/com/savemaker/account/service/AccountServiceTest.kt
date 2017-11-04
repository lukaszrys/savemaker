package com.savemaker.account.service

import com.savemaker.account.domain.Account
import com.savemaker.account.domain.repository.AccountRepository
import com.savemaker.account.domain.dto.AppUserDto
import com.savemaker.account.event.AccountRegisterEventPublisher
import com.savemaker.account.service.exception.AccountAlreadyExistException
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AccountServiceTest {
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
        val account = Account(user.username)
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
        val account = Account(user.username)
        Mockito.`when`(accountRepository.findByEmail(user.username)).thenReturn(Optional.of(account))
        //When
        tested.register(user)
        //Then exception is thrown
    }
}