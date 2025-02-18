Feature: User API

  Scenario: Create User
    Given I create a user
    Then The user should be created

  Scenario: Create User with invalid data
    Given I create a user with invalid data
    Then The user should not be created

  Scenario: Create a existing user
    Given I create a user with the following data
    Then The user should not be created