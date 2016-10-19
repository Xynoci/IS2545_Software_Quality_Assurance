package hw3.steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java8.En;
import hw3.base.BaseGherkin;


/**
 * Created by Xynoci on 10/16/16.
 */
public class AddItemsToCartStepdef extends BaseGherkin implements En {

    public AddItemsToCartStepdef() {
        Given("^I'm on \"([^\"]*)\" page$", (String page) -> {
            super.initDriver(super.DEFAULT_DRIVER);
            switch (page) {
                case "all products":
                    driver.get(BASE_URL + ALL_PRODUCTS);
                    break;
                default:
                    System.err.println("page unrecognized.");
                    break;
            }
        });

        Given("^(\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) already in the cart$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue, Integer iPhone5, Integer magicMouse, Integer iPodNanoBlue) -> {
            int[] numberToBeAdded = new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue},
                    itemList = new int[]{iPhone5, magicMouse, iPodNanoBlue};
            for (int i = 0; i < itemList.length; i++) {
                addItemsToCartOnTheAllPage(numberToBeAdded[i], itemList[i]);
            }
            int expectedTotal = numOfIPhone5 + numOfMagicMouse + numOfIPodNanoBlue,
                    cartTotal = Integer.parseInt(driver.findElement(By.className("count")).getText());
            Assert.assertEquals(expectedTotal, cartTotal);
        });

        Given("^I added (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) to the cart$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue, Integer iPhone5, Integer magicMouse, Integer iPodNanoBlue) -> {
            int[] numberToBeAdded = new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue},
                    itemList = new int[]{iPhone5, magicMouse, iPodNanoBlue};
            for (int i = 0; i < itemList.length; i++) {
                addItemsToCartOnTheAllPage(numberToBeAdded[i], itemList[i]);
            }
            int expectedTotal = numOfIPhone5 + numOfMagicMouse + numOfIPodNanoBlue,
                    cartTotal = Integer.parseInt(driver.findElement(By.className("count")).getText());
            Assert.assertEquals(expectedTotal, cartTotal);
        });

        When("^I try to add (\\d+) (\\d+)$", this::addItemsToCartOnTheAllPage);

        When("^I try to add (\\d+),(\\d+),(\\d+) of (\\d+),(\\d+),(\\d+) into the cart$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue, Integer iPhone5, Integer magicMouse, Integer iPodNanoBlue) -> {
            int[] numberToBeAdded = new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue},
                    itemList = new int[]{iPhone5, magicMouse, iPodNanoBlue};
            for (int i = 0; i < itemList.length; i++) {
                addItemsToCartOnTheAllPage(numberToBeAdded[i], itemList[i]);
            }
        });

        When("^I try to add (\\d+),(\\d+),(\\d+) of \"([^\"]*)\" into the cart from home page$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue, String itemNameList) -> {
            int[] numberToBeAdded = new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue};
            String[] itemList = itemNameList.split(", ");
            for (int i = 0; i < itemList.length; i++) {
                for (int j = 0; j < numberToBeAdded[i]; j++) {
                    WebElement theAnchor = driver.findElement(By.xpath("//div/div/h2[.='" + itemList[i] + "']")),
                            theBuyNowButton = wait.until(ExpectedConditions.elementToBeClickable(theAnchor.findElement(By.xpath("..//..//div[@class='price']/a"))));
                    theBuyNowButton.click();
                    waitUntil(d -> (!d.getCurrentUrl().endsWith(".com/") &&
                            d.findElement(By.id("single_product_page_container")) != null &&
                            d.findElement(By.id("single_product_page_container")).isDisplayed()));
                    int originalCount = Integer.parseInt(driver.findElement(By.className("count")).getText());
                    driver.findElement(By.xpath("//div[@class='input-button-buy']/span/input[@class='wpsc_buy_button']")).submit();
                    waitUntil(d -> Integer.parseInt(d.findElement(By.className("count")).getText()) != originalCount);
                    driver.findElement(By.className("continue_shopping")).click();
                    driver.navigate().to(BASE_URL);
                    waitUntil(d -> d.getCurrentUrl().endsWith(".com/"));
                }
            }
        });

        Then("^I should see total number is (\\d+)$", (Integer expectedCount) -> {
            WebElement cartCounter = driver.findElement(By.className("count"));
            Assert.assertEquals(expectedCount.intValue(), Integer.parseInt(cartCounter.getText()));
        });

        Then("^I should see total number for \"([^\"]*)\" are (\\d+),(\\d+),(\\d+)$", (String itemNameList, Integer ExpectedNumOfIPhone5, Integer ExpectedNumOfMagicMouse, Integer ExpectedNumOfIPodNanoBlue) -> {
            String[] itemNames = itemNameList.split(", ");
            Integer[] ExpectedResults = new Integer[]{ExpectedNumOfIPhone5, ExpectedNumOfMagicMouse, ExpectedNumOfIPodNanoBlue};
            for (int i = 0; i < itemNames.length; i++) {
                WebElement itemRow = driver.findElement(By.xpath("//tr/td/a[.='" + itemNames[i] + "']")),
                        itemQuantity = itemRow.findElement(By.xpath("..//..//input[@name='quantity']"));
                Assert.assertEquals(ExpectedResults[i].intValue(), Integer.parseInt(itemQuantity.getAttribute("value")));
            }
        });

        Then("^I check the shopping cart$", () -> {
            driver.get(BASE_URL + SHOPPING_CART);
        });

        Then("^I move to the home page$", () -> {
            driver.navigate().to(BASE_URL);
        });

        Then("^quit the application$", () -> {
            driver.quit();
        });

    }

    private void addItemsToCartOnTheAllPage(Integer amount, Integer productIndex) {
        if (amount != 0) {
            WebElement itemForm = driver.findElement(By.xpath("//form[@name='product_" + productIndex + "']"));
            for (int i = 0; i < amount; i++) {
                int originalCount = Integer.parseInt(driver.findElement(By.className("count")).getText());
                itemForm.submit();
                waitUntil(d -> Integer.parseInt(d.findElement(By.className("count")).getText()) != originalCount);
                driver.findElement(By.className("continue_shopping")).click();
            }
        }
    }
}
