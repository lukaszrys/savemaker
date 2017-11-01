package com.savemaker.gateway;

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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GatewayApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayApplicationTest {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    public void checkRoutes() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/routes", Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue(entity.getBody().containsKey("/uaa/**"));
        assertTrue(entity.getBody().containsKey("/account/**"));
    }

}
