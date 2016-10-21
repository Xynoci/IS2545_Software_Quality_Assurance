package hw3.gherkins;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

import cucumber.api.java8.En;
import hw3.base.ShoppingCartGherkin;

/**
 * Created by Xynoci on 10/18/16.
 */
public class EditItemsFromCartStepdef extends ShoppingCartGherkin implements En {
    private String[] itemsInCart = new String[]{"iPhone 5", "Magic Mouse", "iPod Nano Blue"};
    private int[] numberToBeAdded = new int[]{2, 1, 1};
    private HashMap<String, Integer> itemMap = new HashMap<>();

    public EditItemsFromCartStepdef() {
        Given("^I got several items in cart$", () -> {
            super.initDriver(super.DEFAULT_DRIVER);
            driver.get(BASE_URL + ALL_PRODUCTS);
            int expectedTotal = 0;
            for (int i = 0; i < itemList.length; i++) {
                addItemsToCartOnTheAllPage(numberToBeAdded[i], itemList[i]);
                itemMap.put(itemsInCart[i], numberToBeAdded[i]);
                expectedTotal += numberToBeAdded[i];
            }
            int cartTotal = Integer.parseInt(driver.findElement(By.className("count")).getText());
            Assert.assertEquals(expectedTotal, cartTotal);
        });

        Given("^I'm about to checkout$", () -> {
            driver.navigate().to(BASE_URL + SHOPPING_CART);
        });

        When("^I try to remove one \"([^\"]*)\" from the list$", (String itemName) -> {
            int originalCount = Integer.parseInt(driver.findElement(By.className("count")).getText());
            WebElement theAnchor = driver.findElement(By.xpath("//tr/td/a[.='" + itemName + "']")),
                    removeButton = theAnchor.findElement(By.xpath("..//..//input[@value='Remove']"));
            removeButton.click();
            waitPageStartRefreshAfterClick(originalCount, "count");
            waitForPageLoaded();
        });

        When("^I modify the amount of \"([^\"]*)\" as (-?\\d+)$", (String item, Integer expectedAmount) -> {
            int originalCount = Integer.parseInt(driver.findElement(By.className("count")).getText());
            WebElement theAnchor = driver.findElement(By.xpath("//tr/td/a[.='" + item + "']")),
                    itemQuantity = theAnchor.findElement(By.xpath("..//..//input[@name='quantity']"));
            itemQuantity.clear();
            itemQuantity.sendKeys(String.valueOf(expectedAmount));
            WebElement updateButton = theAnchor.findElement(By.xpath("..//..//input[@value='Update']"));
            updateButton.click();
            waitPageStartRefreshAfterClick(originalCount, "count");
            waitForPageLoaded();
        });

        Then("^I should see the amount of \"([^\"]*)\" changed corresponding to the (-?\\d+)$", (String item, Integer expectedAmount) -> {
            Assert.assertTrue(expectedAmount <= 0 ? isRemoved(item) : isAmountCorrect(item, expectedAmount));
        });

        Then("^the rest except the modified \"([^\"]*)\" are remains untouched$", (String item) -> {
            Assert.assertTrue(restUntouched(item));
            shutDriverDown();
        });

        Then("^I should see the \"([^\"]*)\" removed from the list$", (String itemName) -> {
            Assert.assertTrue(isRemoved(itemName));
        });

        Then("^the rest except the removed \"([^\"]*)\" are still there$", (String removedItem) -> {
            Assert.assertTrue(restUntouched(removedItem));
            shutDriverDown();
        });
    }

    private boolean isRemoved(String itemName) {
        try {
            driver.findElement(By.xpath("//tr/td/a[.='" + itemName + "']"));
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    private boolean restUntouched(String removedItem) {
        try {
            for (String anItemsInCart : itemsInCart) {
                if (!anItemsInCart.equals(removedItem)) {
                    driver.findElement(By.xpath("//tr/td/a[.='" + anItemsInCart + "']"));
                    Assert.assertTrue(isAmountCorrect(anItemsInCart, itemMap.get(anItemsInCart)));
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isAmountCorrect(String item, int expectedAmount) {
        WebElement theAnchor = driver.findElement(By.xpath("//tr/td/a[.='" + item + "']")),
                itemQuantity = theAnchor.findElement(By.xpath("..//..//input[@name='quantity']"));
        int theAmount = Integer.parseInt(itemQuantity.getAttribute("value"));
        return expectedAmount == theAmount;
    }

    private void waitPageStartRefreshAfterClick(int referenceMarkerValue, String referenceMarkerClassName) {
        try {
            waitUntil(d -> Integer.parseInt(d.findElement(By.className(referenceMarkerClassName)).getText()) != referenceMarkerValue);
        } catch (StaleElementReferenceException e) {
            return;
        }
    }
}
