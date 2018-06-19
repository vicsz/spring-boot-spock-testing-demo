package com.example;

import com.example.springbootspocktestingdemo.SpockTestExampleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpockTestExampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void check_hello_endpoint() {

        String output = restTemplate.getForObject("/", String.class);

        assertEquals(output,"helloworld");

    }
}
