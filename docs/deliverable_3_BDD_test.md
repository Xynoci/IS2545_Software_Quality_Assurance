## IS2545 - DELIVERABLE 3: Testing in BDD [![Build Status](https://travis-ci.org/Xynoci/IS2545_Software_Quality_Assurance.svg?branch=master)](https://travis-ci.org/Xynoci/IS2545_Software_Quality_Assurance)

[Chukun Xia, chx26](mailto:chukun.xia@pitt.edu)

### Tests

#### Test concerns
 

### User Stories and Scenarios

#### Feature: Login to the store

```cucumber
Feature: Login to the store
  As a user
  I would like to log in to my account
  So that I can ... (I don't know 😂)

  Scenario Outline: Login to the store using valid and invalid credentials
    Given I'm about to "login"
    When I try to log in using <name> and <pass>
    Then I should see whether they are <valid> or not

    Examples: Valid credentials
      | name         | pass                | valid     |
      | "test@a"     | "$Q^)0%aWw*AVNLyG"  | "valid"   |
    Examples: Incorrect password
      | name         | pass                | valid     |
      | "test@a"     | "incorrectpassword" | "invalid" |
    Examples: Incorrect user name
      | name         | pass                | valid     |
      | "test@fake"  | "$Q^)0%aWw*AVNLyG"  | "invalid" |
```

#### Feature: Adding items to shopping cart

```cucumber
Feature: Adding items to shopping cart
  As a user
  I would like to add items to my shopping cart
  So that I can place my order later

  Background:
    Given I'm on "all products" page

  Scenario Outline: Adding one item to empty cart from 'All Product'
    When I try to add 1 <item>
    Then I should see total number is 1

    Examples:
      | item name        | item |
      | "iPhone 5"       | 32   |
      | "Magic Mouse"    | 40   |
      | "iPod Nano Blue" | 64   |

  Scenario Outline: Adding bunch of items to cart from 'All Product'
    Given <several> <items> already in the cart
    When I try to add <number> of <items> into the cart
    Then I check the shopping cart
      And I should see total number for <item name> are <total>

    Examples: Items haven't been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,0,1   | 1,1,0  | 1,1,1 |
    Examples: Items have been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 1,1,0  | 1,2,1 |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 2,2,1  | 2,3,2 |

  Scenario Outline: Adding items to cart on different page
    Given I added <several> <items> to the cart
    Then I move to the home page
    When I try to add <number> of <item name> into the cart from home page
    Then I check the shopping cart
      And I should see total number for <item name> are <total>

    Examples: Items haven't been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,0,1   | 1,1,0  | 1,1,1 |
    Examples: Items have been added before
      | item name                               | items    | several | number | total |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 1,1,0  | 1,2,1 |
      | "iPhone 5, Magic Mouse, iPod Nano Blue" | 32,40,64 | 0,1,1   | 2,2,1  | 2,3,2 |
```

#### Feature: Edit item information from shopping cart

```cucumber
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

    Examples:
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
    Examples: reduced the amount to nagetive value
      | named item       | number |
      | "iPod Nano Blue" | -1     |
    Examples: add item
      | named item       | number |
      | "iPhone 5"       | 3      |
      | "Magic Mouse"    | 10     |
```

### Configs for local runs

The tests are currently run on *[Sauce Lab](https://saucelabs.com/)* using remote driver. Uncommenting the `initLocalDriver(driverType)` and commenting the `initRomoteDriver()` to run them on local.

```java
    protected void initDriver(int driverType) {
        //initLocalDriver(driverType);
        initRomoteDriver();
        wait = new WebDriverWait(driver, 60);
    }
```

### References and notes
- Sauce Lab: [Write Great Cucumber Tests](https://saucelabs.com/blog/write-great-cucumber-tests)
- [Cucumber Best Practices](https://github.com/strongqa/howitzer/wiki/Cucumber-Best-Practices)
- [Using Sauce Labs with Travis CI](https://docs.travis-ci.com/user/sauce-connect/)
- Sauce Lab: [Java Test Setup Example](https://wiki.saucelabs.com/display/DOCS/Java+Test+Setup+Example)
- Sauce Lab: [Best Practices for Running Tests](https://wiki.saucelabs.com/display/DOCS/Best+Practices+for+Running+Tests)


