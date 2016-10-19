Feature: Login to the store
  Scenario Outline: Login to the store using valid and invalid credentials
    Given I'm about to "login"
    When I try to log in using <name> and <pass>
    Then I should see whether they are <valid> or not
      And I am done

    Examples: Valid credentials
      | name         | pass                | valid     |
      | "test@a"     | "$Q^)0%aWw*AVNLyG"  | "valid"   |
    Examples: Incorrect user name
      | name         | pass                | valid     |
      | "test@fake"  | "$Q^)0%aWw*AVNLyG"  | "invalid" |
      | "test@wrong" | "$Q^)0%aWw*AVNLyG"  | "invalid" |
    Examples: Incorrect password
      | name         | pass                | valid     |
      | "test@a"     | "incorrectpassword" | "invalid" |
