package hw3.base;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java8.En;

/**
 * Created by Xynoci on 10/19/16.
 */
public class ShoppingCartGherkin extends BaseGherkin implements En {
    protected final int iPhone5 = 32;
    protected final int magicMouse = 40;
    protected final int iPodNanoBlue = 64;
    protected final int[] itemList = new int[]{iPhone5, magicMouse, iPodNanoBlue};
    protected final String[] itemNameList = new String[]{"iPhone 5", "Magic Mouse", "iPod Nano Blue"};

    protected ShoppingCartGherkin(){

    }

    protected void addItemsToCartOnTheAllPage(Integer amount, Integer productIndex) {
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

    protected boolean addItemBundleToCart(int[] numberToBeAdded) {
        int expectedTotal = 0;
        for (int i = 0; i < itemList.length; i++) {
            addItemsToCartOnTheAllPage(numberToBeAdded[i], itemList[i]);
            expectedTotal += numberToBeAdded[i];
        }
        int cartTotal = Integer.parseInt(driver.findElement(By.className("count")).getText());
        return expectedTotal == cartTotal;
    }

    protected boolean checkTheBundleInShoppingCart(Integer[] ExpectedResults){
        for (int i = 0; i < itemNameList.length; i++) {
            WebElement theAnchor = driver.findElement(By.xpath("//tr/td/a[.='" + itemNameList[i] + "']")),
                    itemQuantity = theAnchor.findElement(By.xpath("..//..//input[@name='quantity']"));
            if(ExpectedResults[i] !=Integer.parseInt(itemQuantity.getAttribute("value")))
                return false;
        }
        return true;
    }

}
