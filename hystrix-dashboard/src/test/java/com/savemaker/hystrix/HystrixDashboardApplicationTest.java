package com.savemaker.hystrix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HystrixDashboardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HystrixDashboardApplicationTest {

    @LocalServerPort
    private int port = 0;

    @Test
    public void checkHystrix() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<String> entity = new TestRestTemplate().getForEntity("http://localhost:" + port + "/hystrix", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        //assertTrue(entity.getBody().containsKey("/uaa/**"));
        //assertTrue(entity.getBody().containsKey("/account/**"));
    }

}
