Feature: Pet API

  Scenario: Create Pet
    Given I create a pet with the following data
      | id | name  | categoryId | categoryName | photoUrls | tags          | status    |
      | 1  | Greta | 1          | cat          | cat1.jpg  | 1:tag1,2:tag2 | available |

    Then status code should be 200
    And The pet should be created

  Scenario: Create Pet with invalid data
    Given I create a pet with invalid data
    Then status code should be 400
    And The pet should not be created

  Scenario: Create a existing pet
    Given I create a pet with the following data
      | id | name  | categoryId | categoryName | photoUrls | tags          | status    |
      | 1  | Greta | 1          | cat          | cat1.jpg  | 1:tag1,2:tag2 | available |
    Then status code should be 400
    And The pet should not be created

  Scenario: Get pet by id
    Given I create a pet with the following data
      | id | name | categoryId | categoryName | photoUrls | tags   | status    |
      | 2  | Vina | 1          | cat          | cat1.jpg  | 1:tag1 | available |
    When I get the pet by id
    Then status code should be 200
    And The pet should be returned


  Scenario: Delete pet by id
    Given I create a pet with the following data
      | id | name  | categoryId | categoryName | photoUrls | tags   | status    |
      | 3  | Petar | 2          | dog          | cat1.jpg  | 3:tag3 | available |
    When I get the pet by id
    Then status code should be 200
    And The pet should be returned
    When I delete the pet by id
    Then status code should be 200

    Scenario: Pet not found
      When I get the pet by 100
      Then status code should be 404