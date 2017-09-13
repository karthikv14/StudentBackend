Feature: Student Resource Test

Scenario: create a student
    Given the web context is set
    When client request POST /students with json data:
    """
    {"firstName":"John","lastName":"Doe"}
    """
    Then the post response code should be 201

Scenario: update a student
    Given the web context is set
    When client request PUT /students with json data:
    """
    {"id":1,"firstName":"Jane","lastName":"Doe"}
    """
    Then the put response code should be 200

Scenario: delete a student
    Given the web context is set
    When client request DELETE /students/$1
    Then the delete response code should be 200

Scenario: Get all students
    Given the web context is set
    When client request GET /students
    Then the Get All response should be 200
    And the JSON response should be an array with 2 students

Scenario: Get student by id
    Given the web context is set
    When client request GET /students/1
    Then the Get by ID response should be 200
    And the JSON response should be a customer object

    