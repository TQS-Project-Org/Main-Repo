Feature: User Login

  Scenario: Successful login
    Given a login request with username "john" and password "password"
    When the login request is sent
    Then the login response status code should be 200

  Scenario: Invalid login credentials
    Given a login request with username "john" and password "incorrect"
    When the login request is sent
    Then the login response status code should be 401