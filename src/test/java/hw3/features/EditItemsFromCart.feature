Feature: Edit item information from shopping cart
  As a user
  I would like to change number of items to checkout in my shopping cart
  So that I can easily modify my order before checkout

  Background: # 2,1,1 for item 32, 40 and 64 respectively
    Given I got several items in cart
    And I'm about to checkout

  Scenario Outline: Remove items using remove button
    When I try to remove one <named item> from the list
    Then I should see the <named item> removed from the list
      And the rest except the removed <named item> are still there

    Examples: remove item
      | named item       | item |
      | "iPhone 5"       | 32   |
      | "Magic Mouse"    | 40   |
      | "iPod Nano Blue" | 64   |

  Scenario Outline: Modify amount of certain item
    When I modify the amount of <named item> as <number>
    Then I should see the amount of <named item> changed corresponding to the <number>
    And the rest except the modified <named item> are remains untouched

    Examples: reduce the amount (not to zero)
      | named item       | number |
      | "iPhone 5"       | 1      |
    Examples: reduced the amount to zero
      | named item       | number |
      | "Magic Mouse"    | 0      |
    Examples: reduced the amount to negative value
      | named item       | number |
      | "iPod Nano Blue" | -1     |
    Examples: add items
      | named item       | number |
      | "iPhone 5"       | 3      |
      | "Magic Mouse"    | 10     |