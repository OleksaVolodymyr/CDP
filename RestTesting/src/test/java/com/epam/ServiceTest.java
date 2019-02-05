package com.epam;


import com.cdp.model.User;
import com.cdp.utils.Parser;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.given;

public class ServiceTest {


    @Test(dataProvider = "validBoundaryValues")
    public void BoundaryValidTest(User user) {
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(
                "http" +
                        "://localhost:8080/LogService" +
                        "/rest" +
                        "/logs" +
                        "/addUser").then().statusCode(200);
    }

    @Test(dataProvider = "invalidBoundaryValues")
    public void BoundaryInvalidTest(User user) {
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(
                "http" +
                        "://localhost:8080/LogService" +
                        "/rest" +
                        "/logs" +
                        "/addUser").then().statusCode(422).body("Msg", Matchers.is("User has invalid age"));
    }

    @Test(dataProvider = "equivalentPartitioningValues")
    public void EQValidTest(User user) {
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(
                "http" +
                        "://localhost:8080/LogService" +
                        "/rest" +
                        "/logs" +
                        "/addUser").then().statusCode(200);
    }

    @DataProvider(name = "validBoundaryValues")
    public Iterator<User> validBoundaryValues() {
        return Parser.parseCSV("./resources/uservalid.csv", User.class, ",").iterator();
    }

    @DataProvider(name = "invalidBoundaryValues")
    public Iterator<User> invalidBoundaryValues() {
        return Parser.parseCSV("./resources/userinvalid.csv", User.class,
                ",").iterator();
    }

    @DataProvider(name = "equivalentPartitioningValues")
    public Iterator<User> equivalentPartitioningValues() {
        return Parser.parseCSV("./resources/usereqvalid.csv", User.class,
                ",").iterator();
    }


}
