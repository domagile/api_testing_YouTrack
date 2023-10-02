package api.youtrack.issuecrud;
import api.youtrack.util.ResourceReader;
import api.youtrack.config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import static api.youtrack.constants.Constants.Endpoints.*;
import static api.youtrack.constants.Constants.ApiConfiguration.AUTHORIZATION_VALUE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CRUDTest extends TestConfig {
    private String issueId;
    private static final String GET_OPTIONS = "fields=id,summary,customFields(id,name,value(avatarUrl,buildLink,color(id),fullName,id,isResolved,localizedName,login,minutes,name,presentation,text))";
    private static final String UPDATE_OPTIONS = "fields=id,summary,customFields(id,name,value(avatarUrl,buildLink,color(id),fullName,id,isResolved,localizedName,login,minutes,name,presentation,text))";
    private static final String CHECK_DELETE_ISSUE_OPTIONS = "fields=id,summary,customFields(id,name,value(avatarUrl,buildLink,color(id),fullName,id,isResolved,localizedName,login,minutes,name,presentation,text))";

    @Test
    @Order(1)
    public void createIssue() {
        String requestBody = ResourceReader.read("createIssue.json");

        Response response = given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(ISSUES);
        issueId = response.body().jsonPath().getString("id");

        response.then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(2)
    public void getIssue() {
        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .get("{issueEndpoint}/{issueId}?{options}", ISSUES, issueId, GET_OPTIONS)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("summary", equalTo("Test for CRUD of Issue"));
    }

    @Test
    @Order(3)
    public void updateIssue() {
        String updateBody = ResourceReader.read("updateIssue.json");
        given().body(updateBody)
                .header("Authorization", AUTHORIZATION_VALUE)
                .contentType(ContentType.JSON)
                .when()
                .post("{issueEndpoint}/{issueId}?{options}", ISSUES, issueId, UPDATE_OPTIONS)

                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("summary", equalTo("Updated issue"))
                .time(lessThan(1000L));
    }

    @Test
    @Order(4)
    public void deleteIssue() {
        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .when()
                .delete("{issueEndpoint}/{issueId}", ISSUES, issueId)
                .then().log().body().statusCode(HttpStatus.SC_OK);

        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .get("{issueEndpoint}/{issueId}?{options}", ISSUES, issueId, CHECK_DELETE_ISSUE_OPTIONS)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .body(containsString("Not Found"));
    }
}

