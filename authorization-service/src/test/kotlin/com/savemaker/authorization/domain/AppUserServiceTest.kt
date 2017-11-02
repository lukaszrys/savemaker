package com.savemaker.authorization.domain

import com.savemaker.authorization.domain.repository.AppUserRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class AppUserServiceTest {

    @Mock
    lateinit var appUserRepository : AppUserRepository

    lateinit var tested: AppUserService

    @Before
    fun setupMock(){
        tested = AppUserService(appUserRepository)
    }
    @Test
    fun shouldRegisterUser() {
        val anyUser = Mockito.any(AppUser::class.java)
        //Given
        val user = AppUser("user","password","email@email.com")
        Mockito.`when`(appUserRepository.findByUsername(user.username)).thenReturn(Optional.empty())
        Mockito.`when`(appUserRepository.save(user)).thenReturn(user)
        //When
        tested.registerUser(user)
        //Then
        Mockito.verify(appUserRepository, Mockito.times(1)).findByUsername(user.username)
        //Mockito.verify(appUserRepository, Mockito.times(1)).save(anyUser)
    }
}