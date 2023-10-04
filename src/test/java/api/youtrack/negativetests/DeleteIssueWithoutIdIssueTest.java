package api.youtrack.negativetests;

import api.youtrack.config.TestConfig;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static api.youtrack.constants.Constants.Endpoints.ISSUES;
import static api.youtrack.constants.Constants.ApiConfiguration.AUTHORIZATION_VALUE;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.StringContains.containsString;

public class DeleteIssueWithoutIdIssueTest extends TestConfig {
    @Test
    public void deleteIssueIfIdNotSpecified() {
        given()
                .header("Authorization", AUTHORIZATION_VALUE)
                .delete(ISSUES)
                .then().statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                .statusLine(containsString("Method Not Allowed"));
    }
}