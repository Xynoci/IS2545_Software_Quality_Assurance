#Feature: Login to the store
#  As a user
#  I would like to log in to my account
#  So that I can ... (I don't know 😂)
#
#  Scenario Outline: Login to the store using valid and invalid credentials
#    Given I'm about to "login"
#    When I try to log in using <name> and <pass>
#    Then I should see whether they are <valid> or not
#
#    Examples: Valid credentials
#      | name         | pass                | valid     |
#      | "test@a"     | "$Q^)0%aWw*AVNLyG"  | "valid"   |
#    Examples: Incorrect password
#      | name         | pass                | valid     |
#      | "test@a"     | "incorrectpassword" | "invalid" |
#    Examples: Incorrect user name
#      | name         | pass                | valid     |
#      | "test@fake"  | "$Q^)0%aWw*AVNLyG"  | "invalid" |