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

public class UpdateIssueWithEmptyBodyTest extends TestConfig {

    private static final String ISSUE_ID = "TAPI-21";
    private static final String OPTIONS = "fields=id,summary,customFields(id,name,value(avatarUrl,buildLink,color(id),fullName,id,isResolved,localizedName,login,minutes,name,presentation,text))";

    @Test
    public void updateIssueWithEmptyBody() {
        String emptyBody = ResourceReader.read("emptyBody.json");
        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .contentType(ContentType.JSON)
                .body(emptyBody)
                .post("{issueEndpoint}/{issueId}?{options}", ISSUES, ISSUE_ID, OPTIONS)
                .then().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", containsString("bad_request"))
                .body("error_description", containsString("Empty request body is not allowed for POST requests"));
    }
}
