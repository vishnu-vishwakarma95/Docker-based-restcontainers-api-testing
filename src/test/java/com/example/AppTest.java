package com.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AppTest {

    private static GenericContainer<?> mockServer;

    @BeforeAll
    public static void setUp() {
        mockServer = new GenericContainer<>("mockserver/mockserver:latest")
                        .withExposedPorts(1080);
        mockServer.start();

        RestAssured.baseURI = "http://" + mockServer.getHost();
        RestAssured.port = mockServer.getFirstMappedPort();
    }

    @AfterAll
    public static void tearDown() {
        if (mockServer != null) {
            mockServer.stop();
        }
    }

    @Test
    public void testGetEndpoint() {
        given()
            .when()
                .get("/endpoint")
            .then()
                .statusCode(200)
                .body("key", equalTo("value"));
    }
}
