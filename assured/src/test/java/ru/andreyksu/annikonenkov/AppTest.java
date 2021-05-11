package ru.andreyksu.annikonenkov;

import static org.junit.Assert.assertTrue;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Matcher;

import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private String firstEndPointParam = "https://jsonplaceholder.typicode.com";

    private String someAddedString = "{verb}/{id}/comments";
    // private String someAddedString = "posts/1/comments";

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {

        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri(firstEndPointParam)
                .addPathParam("verb", "posts").setAccept(ContentType.JSON).setContentType(ContentType.JSON)
                .setContentType(ContentType.XML).setContentType("charset=utf-8").addCookie("pass", "ddddddddddddddd")
                .log(LogDetail.ALL).build();

        CustomMatcher<String> matcher = new CustomMatcher<String>("is Empty body?") {

            public boolean matches(Object object) {
                return ((object instanceof String) && !((String) object).isEmpty()
                        && ((String) object).contains("postId"));
            }
        };

        ResponseSpecification responseSpec =
                new ResponseSpecBuilder().expectStatusCode(200).expectBody(matcher).log(LogDetail.ALL).build();
        for (int i = 1; i <= 1; i++) {
            // Response response = given().spec(requestSpec).pathParam("id", i).when().get(someAddedString);
            Response response = given().spec(requestSpec).when().get(someAddedString, "1");
            JsonPath jsonPath = response.then().spec(responseSpec).extract().jsonPath();

            System.out.println("==============================================================================");
            System.out.println("-----------------------------contentType--------------------------------------");
            System.out.println(response.contentType());
            System.out.println("-----------------------------getTime------------------------------------------");
            System.out.println(response.getTime());
            System.out.println("-----------------------------getStatusCode------------------------------------");
            System.out.println(response.getStatusCode());
            System.out.println("-----------------------------getContentType-----------------------------------");
            System.out.println(response.getContentType());
            System.out.println("-----------------------------headers------------------------------------------");
            System.out.println(response.headers());
            System.out.println("-----------------------------cookies------------------------------------------");
            System.out.println(response.cookies());
            System.out.println("-----------------------------detailedCookies----");
            System.out.println(response.detailedCookies());
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(response.body().asPrettyString());
            System.out.println("------------------------------------------------------------------------------");
            // System.out.println(response.asPrettyString());
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        }
    }
}
