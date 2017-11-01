package com.savemaker.authorization.domain

import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import java.util.*

class AppUserServiceTest {

    private var appUserRepository : AppUserRepository = mock(AppUserRepository::class.java)

    private var tested: AppUserService = AppUserService(appUserRepository)

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
        //verify(appUserRepository, times(1)).save(anyUser)
    }
}