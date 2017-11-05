package com.savemaker.account

import com.mongodb.MongoClient
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.test.context.junit4.SpringRunner

@Configuration
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(AccountApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//TODO("extract to commons?")
abstract class IntegrationTestConfig : AbstractMongoConfiguration() {

    @Value("\${local.server.port}")
    protected val port = 0

    protected val testRestTemplate : TestRestTemplate = TestRestTemplate()

    @Autowired
    lateinit var env : Environment

    override fun mongo(): MongoClient {
        return MongoClient("127.0.0.1")
    }

    override fun getDatabaseName(): String {
        return env.getRequiredProperty("spring.data.mongodb.database")
    }

}