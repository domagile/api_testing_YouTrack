package api.youtrack.negativetests;

import api.youtrack.config.TestConfig;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static api.youtrack.constants.Constants.ApiConfiguration.AUTHORIZATION_VALUE;
import static api.youtrack.constants.Constants.Endpoints.ISSUES;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class IncorrectIssueIdInQueryTest extends TestConfig {
    private static final String INCORRECT_ISSUE_ID = "TAPI-2";
    private static final String OPTIONS = "fields=id,summary,customFields(id,name,value(avatarUrl,buildLink,color(id),fullName,id,isResolved,localizedName,login,minutes,name,presentation,text))";

    @Test
    public void getIssueWithIncorrectID() {
        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .get("/{issueEndpoint}/{incorrectID}?{options}", ISSUES, INCORRECT_ISSUE_ID, OPTIONS)
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .statusLine(containsString("Not Found"));
    }
}
