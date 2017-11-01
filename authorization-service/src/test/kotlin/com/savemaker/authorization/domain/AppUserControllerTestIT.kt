package com.savemaker.authorization.domain

import com.savemaker.authorization.IntegrationTestConfig
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class AppUserControllerTestIT : IntegrationTestConfig(){

    @Autowired
    lateinit var appUserRepository : AppUserRepository

    @Before
    fun reset() {
        mongoTemplate().dropCollection(AppUser::class.java)
        //TODO: save using mongoTemplate
        appUserRepository.save(existingAppUser())
    }

    @Test
    fun shouldRegisterUser() {
        //Given
        val user = AppUser("admin","admin","admin@admin.com")

        //When
        val responseUser : ResponseEntity<AppUser> = testRestTemplate.postForEntity(registerUri(), user, AppUser::class.java)

        //Then
        Assert.assertEquals(responseUser.statusCode, HttpStatus.CREATED)
        Assert.assertEquals(responseUser.body?.username, user.username)
        Assert.assertEquals(responseUser.body?.email, user.email)
    }

    @Test
    fun shouldNotRegisterUser() {
        //Given
        val user = existingAppUser()

        //When
        val responseUser : ResponseEntity<Any> = testRestTemplate.postForEntity(registerUri(), user, Any::class.java)

        //Then
        Assert.assertEquals(responseUser.statusCode, HttpStatus.BAD_REQUEST)
    }

    private fun registerUri(): String {
        return "http://localhost:$port/register"
    }
}