package com.epam;


import com.cdp.model.User;
import com.epam.dataprovider.TestData;
import com.epam.dataprovider.annotation.SourcePath;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test(dataProviderClass = TestData.class, dataProvider = "CSVData")
public class ServiceTest {

    private static final String ADD_USER_URL = "http://localhost:8080/LogService/logs/addUser";

    @SourcePath(path = "./resources/uservalid.csv", model = User.class)
    public void BoundaryValidTest(User user) {
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(ADD_USER_URL)
                .then().statusCode(200);
    }

    @SourcePath(path = "./resources/userinvalid.csv", model = User.class)
    public void BoundaryInvalidTest(User user) {
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(ADD_USER_URL)
                .then().statusCode(422).body("Msg", Matchers.is("User has invalid age"));
    }

    @SourcePath(path = "./resources/usereqvalid.csv", model = User.class)
    public void EQValidTest(User user) {
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(ADD_USER_URL)
                .then().statusCode(200);
    }

}
