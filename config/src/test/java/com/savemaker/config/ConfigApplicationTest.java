package com.savemaker.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConfigApplicationTest {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    public void configurationSuccess() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/app/cloud", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}
