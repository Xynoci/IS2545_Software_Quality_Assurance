package hw3.gherkins;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import cucumber.api.java8.En;
import hw3.base.ShoppingCartGherkin;


/**
 * Created by Xynoci on 10/16/16.
 */
public class AddItemsToCartStepdef extends ShoppingCartGherkin implements En {

    public AddItemsToCartStepdef() {
        Given("^I'm on \"([^\"]*)\" page$", (String page) -> {
            super.initDriver(super.DEFAULT_DRIVER);
            navigateTo(page);
        });

        Given("^(\\d+),(\\d+),(\\d+) items already in the cart$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue) -> {
            Assert.assertTrue(addItemBundleToCart(new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue}));
        });

        Given("^I added (\\d+),(\\d+),(\\d+) items to the cart$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue) -> {
            Assert.assertTrue(addItemBundleToCart(new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue}));
        });

        When("^I try to add (\\d+) (\\d+)$", this::addItemsToCartOnTheAllPage);

        When("^I try to add (\\d+),(\\d+),(\\d+) of items into the cart$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue) -> {
            int[] numberToBeAdded = new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue};
            for (int i = 0; i < itemList.length; i++) {
                addItemsToCartOnTheAllPage(numberToBeAdded[i], itemList[i]);
            }
        });

        When("^I try to add (\\d+),(\\d+),(\\d+) of items into the cart from home page$", (Integer numOfIPhone5, Integer numOfMagicMouse, Integer numOfIPodNanoBlue) -> {
            int[] numberToBeAdded = new int[]{numOfIPhone5, numOfMagicMouse, numOfIPodNanoBlue};
            for (int i = 0; i < itemNameList.length; i++) {
                for (int j = 0; j < numberToBeAdded[i]; j++) {
                    WebElement theAnchor = driver.findElement(By.xpath("//div/div/h2[.='" + itemNameList[i] + "']")),
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
            shutDriverDown();
        });

        Then("^I should see total number of items are (\\d+),(\\d+),(\\d+)$", (Integer ExpectedNumOfIPhone5, Integer ExpectedNumOfMagicMouse, Integer ExpectedNumOfIPodNanoBlue) -> {
            Assert.assertTrue(checkTheBundleInShoppingCart(new Integer[]{ExpectedNumOfIPhone5, ExpectedNumOfMagicMouse, ExpectedNumOfIPodNanoBlue}));
            shutDriverDown();
        });

        Then("^I navigate to \"([^\"]*)\"$", this::navigateTo);

    }

}
