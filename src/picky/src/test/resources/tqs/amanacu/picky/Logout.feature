Feature: User Logout

  Scenario: Successful logout
    Given a user is logged in
    When the user logs out
    Then the logout response status code should be 200