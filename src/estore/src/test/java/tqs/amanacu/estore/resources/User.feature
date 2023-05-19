Feature: User Management

  Scenario: Create a user
    Given a user with username "John" and email "john@example.com" and password "password"
    When the user is created
    Then the user's username should be "John"
    And the user's email should be "john@example.com"
    And the user's password should be "password"