Feature: Adding items to shopping cart
  Scenario Outline: Adding one item to empty cart from 'All Product'
    Given I'm on "all products" page
    When I try to add 1 <item>
    Then I should see total number is 1
      And quit the application

    Examples:
      | item name        | item |
      | "iPhone 5"       | 32   |
      | "Magic Mouse"    | 40   |
      | "iPod Nano Blue" | 64   |

  Scenario Outline: Adding one item to cart already contains item from 'All Product'
    Given I'm on "all products" page
      And <several> <items> already in the cart
    When I try to add <number> of <items> into the cart
    Then I check the shopping cart
      And I should see total number for <item name> are <total>
      And quit the application

    Examples: Items haven't been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,0,1   | 1,1,0  | 1,1,1 |
    Examples: Items have been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 1,1,0  | 1,2,1 |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 1,1,1   | 1,1,1  | 2,2,2 |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 4,2,1  | 4,3,2 |

  Scenario Outline: Adding items to cart on different page
    Given I'm on "all products" page
      And I added <several> <items> to the cart
    Then I move to the home page
    When I try to add <number> of <item name> into the cart from home page
    Then I check the shopping cart
      And I should see total number for <item name> are <total>
      And quit the application

    Examples: Items haven't been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,0,1   | 1,1,0  | 1,1,1 |
    Examples: Items have been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 1,1,0  | 1,2,1 |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 1,1,1   | 1,1,1  | 2,2,2 |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 4,0,1  | 4,1,2 |