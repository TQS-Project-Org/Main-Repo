Feature: User Registration

  Scenario: Successful user registration
    Given a registration request with username "john", email "john@example.com", and password "password"
    When the registration request is sent
    Then the registration response status code should be 200

  Scenario: Username already taken
    Given a registration request with username "john", email "john@example.com", and password "password"
    And a user with the username "john" already exists
    When the registration request is sent
    Then the registration response status code should be 400

  Scenario: Email already in use
    Given a registration request with username "john", email "john@example.com", and password "password"
    And a user with the email "john@example.com" already exists
    When the registration request is sent
    Then the registration response status code should be 400