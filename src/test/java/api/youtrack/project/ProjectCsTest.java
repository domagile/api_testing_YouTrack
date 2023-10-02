package api.youtrack.project;

import api.youtrack.config.TestConfig;
import api.youtrack.util.ResourceReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static api.youtrack.constants.Constants.ApiConfiguration.AUTHORIZATION_VALUE;
import static api.youtrack.constants.Constants.Endpoints.PROJECT;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectCsTest extends TestConfig {
    public static String CREATE_PROJECT_OPTIONS = "fields=shortName,name,leader(id,login,name)&template=scrum";
    public static String PROJECT_NAME = "CS";
    public static String GET_PROJECTS_OPTIONS = "fields=name,shortName,createdBy(login,name,id),leader(login,name,id),key&$top=10";

    @Test
    @Order(1)
    public void createProjectCs() {
        String requestBody = ResourceReader.read("createNewProject.json");
        String path = PROJECT + '?' + CREATE_PROJECT_OPTIONS;
        Response response = given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(path);

        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(2)
    public void getAllProjects() {
        String path = PROJECT + '?' + GET_PROJECTS_OPTIONS;
        Response response = given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .get(path);
        response.then().statusCode(HttpStatus.SC_OK);
        String expectedJSON = ResourceReader.read("expectedResponseAllProjects.json");

        String actualJSON = response.getBody().asString();
        assertEquals(expectedJSON, actualJSON);
    }

    @Test
    @Order(3)
    public void deleteProjectCs() {
        String path = PROJECT + '/' + PROJECT_NAME;
        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .delete(path)
                .then().statusCode(HttpStatus.SC_OK);

        given().header("Authorization", AUTHORIZATION_VALUE)
                .get(path)
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}

