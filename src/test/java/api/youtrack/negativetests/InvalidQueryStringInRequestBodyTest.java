package api.youtrack.negativetests;

import api.youtrack.config.TestConfig;
import api.youtrack.util.ResourceReader;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static api.youtrack.constants.Constants.ApiConfiguration.AUTHORIZATION_VALUE;
import static api.youtrack.constants.Constants.Endpoints.ISSUES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class InvalidQueryStringInRequestBodyTest extends TestConfig {
    private static final String ISSUE_ID = "TAPI-27";
    private static final String OPTIONS = "fields=id,summary,customFields(id,name,value(avatarUrl,buildLink,color(id),fullName,id,isResolved,localizedName,login,minutes,name,presentation,text))30";
    @Test
    public void createIssue() {
        String requestBody = ResourceReader.read("invalidBody.json");

        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("{issueEndpoint}/{issueId}?{options}", ISSUES, ISSUE_ID, OPTIONS)
                .then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", containsString("bad_request"))
                .body("error_description", containsString("Query string has invalid syntax"));
    }
}