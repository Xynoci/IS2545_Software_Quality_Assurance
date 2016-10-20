#Feature: Adding items to shopping cart
#  As a user
#  I would like to add items to my shopping cart
#  So that I can place my order later
#
#  Background:
#    Given I'm on "all products" page
#
#  Scenario Outline: Adding one item to empty cart from 'All Product'
#    When I try to add 1 <item>
#    Then I should see total number is 1
#
#    Examples:
#      | item name        | item |
#      | "iPhone 5"       | 32   |
#      | "Magic Mouse"    | 40   |
#      | "iPod Nano Blue" | 64   |
#
#  Scenario Outline: Adding bunch of items to cart from 'All Product'
#    Given <several> items already in the cart
#    When I try to add <number> of items into the cart
#    Then I check the shopping cart
#      And I should see total number of items are <total>
#
#    Examples: Items haven't been added before
#      | several | number | total |
#      | 0,0,1   | 1,1,0  | 1,1,1 |
#    Examples: Items have been added before
#      | several | number | total |
#      | 0,1,1   | 1,1,0  | 1,2,1 |
#      | 0,1,1   | 2,2,1  | 2,3,2 |
#
#  Scenario Outline: Adding items to cart on different page
#    Given I added <several> items to the cart
#    Then I move to the home page
#    When I try to add <number> of items into the cart from home page
#    Then I move to the shopping cart
#      And I will find total number of items are <total>
#
#    Examples: Items haven't been added before
#      | several | number | total |
#      | 0,0,1   | 1,1,0  | 1,1,1 |
#    Examples: Items have been added before
#      | several | number | total |
#      | 0,1,1   | 1,1,0  | 1,2,1 |
#      | 0,1,1   | 2,2,1  | 2,3,2 |