package ru.andreyksu.annikonenkov;

import static org.junit.Assert.assertTrue;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.response.Response;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Response resp =get("https://jsonplaceholder.typicode.com/todos/1");
        
        resp.then().body("userId", equalTo(1));
        
        System.out.println(resp.asString());
        
        resp.then().body("userId", equalTo(2));
        
    }
}
