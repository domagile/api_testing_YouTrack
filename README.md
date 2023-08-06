
## Introduction

API testing was conducted using the Postman tool. The API documentation used for the testing can be found [here](https://www.jetbrains.com/help/youtrack/devportal/youtrack-rest-api.html).

All Postman requests associated with this project can be found [here](https://www.postman.com/winter-astronaut-424930/workspace/api-testing-youtrack/overview).

## Test Execution

All tests were organized and executed through various collections. Additionally, variables were created, both global and collection-specific, to facilitate the testing process.

## Test Collections

### CRUD Issue Test Collection

This collection tests the Create, Read, Update, and Delete (CRUD) operations for Issues on YouTrack. 

### Negative Test Collection

The Negative Test Collection is designed to verify the system's behavior under exceptional circumstances, ensuring that it handles errors gracefully and returns appropriate responses. It includes the following scenarios:

- POST request with a body content type of TEXT instead of the required JSON, expected to result in a 400 error.
- POST request to update an issue with an empty body.
- POST request with an empty mandatory field in the body.
- GET request with an invalid token.
- GET request executed by an unauthorized user.
- POST request with an incorrect issue ID in the request line.
- DELETE request where the project to be deleted is not specified in the request line.
- POST request when creating an issue with a JSON body and a content-type of XML.

### Project Management Test Collection

This collection focuses on the operations related to management of Project in YouTrack. The tests are:

- Retrieving a list of all projects.
- Obtaining all issues within a specific project.
- Deleting a specific project.
- Check that specific project was deleted.

## Conclusion

YouTrack REST API testing was done using Postman, focusing on both typical use cases (CRUD operations) and edge cases (collection of negative tests). This experience demonstrates my ability to effectively test REST APIs in both positive and negative scenarios. 

