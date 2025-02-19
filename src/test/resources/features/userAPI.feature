Feature: User API

  Scenario: Create User
    Given I create a user with the following data
    | id | username | firstName | lastName | email | password | phone | userStatus |
    | 1  | laura43  | Laura     | Castillo | laura43@gmail.com | 123456 | 123456789 | 1 |

    Then status code is 200
    And The user should be created

  Scenario: Create User with invalid data
    Given I create a user with invalid data
    Then status code is 400
    And The user should not be created

  Scenario: Create a existing user
    Given I create a user with the following data
      | id | username | firstName | lastName | email | password | phone | userStatus |
      | 1  | laura43  | Laura     | Castillo | laura43@gmail.com | 123456 | 123456789 | 1 |
    Then status code is 400
    And The user should not be created

  Scenario: Get user by username
    Given I create a user with the following data
        | id | username | firstName | lastName | email             | password | phone     | userStatus |
        | 2  | camila23 | Camila    | Castillo | camila23@gmail.com | 123456   | 123456789 | 1          |
    When I get the user by username
    Then status code is 200
    And The user should be returned


  Scenario: Delete user by username
    Given I create a user with the following data
        | id | username | firstName | lastName | email             | password | phone     | userStatus |
        | 3  | camila23 | Camila    | Castillo | camila23@gmail.com | 123456   | 123456789 | 1          |
    When I get the user by username
    Then status code is 200
    And The user should be returned
    When I delete the user by username
    Then status code is 200

    Scenario: User not found
      When I get the user by "camila24"
      Then status code is 404