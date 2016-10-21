package hw3.base;

import com.google.common.base.Predicate;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import cucumber.api.java8.En;
import hw3.utils.OSValidator;

/**
 * Created by Xynoci on 10/16/16.
 */
public class BaseGherkin implements En {
    public final int DEFAULT_DRIVER = 1;
    public final int FIREFOX = 2;
    public final int CHROME = 3;
    protected final String BASE_URL = "http://store.demoqa.com/";
    protected final String SHOPPING_CART = "products-page/checkout/";
    protected final String ALL_PRODUCTS = "products-page/product-category/";
    protected final String LOG_IN = "tools-qa/";
    private final String SAUCE_USERNAME = System.getenv("SAUCE_USERNAME");
    private final String SAUCE_ACCESS_KEY = System.getenv("SAUCE_ACCESS_KEY");
    private final String URL = "http://" + SAUCE_USERNAME + ":" + SAUCE_ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void initDriver(int driverType) {
//        initLocalDriver(driverType);
        initRomoteDriver();
        wait = new WebDriverWait(driver, 60);
    }

    private void initLocalDriver(int driverType) {
        String osPath = OSValidator.getOSAndArchString(),
                suffix = OSValidator.isWindows() ? ".exe" : "",
                driverPath = "src/test/resources/driver/" + osPath + "/geckodriver" + suffix;
        System.setProperty("webdriver.gecko.driver", driverPath);
        switch (driverType) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
    }

    private void initRomoteDriver() {
        DesiredCapabilities caps = DesiredCapabilities.firefox();
        caps.setCapability("platform", "OS X 10.11");
        caps.setCapability("version", "49.0");
        caps.setCapability("name", "IS2545 Web BDD Test");
        caps.setCapability("tags", "Complete");
        caps.setCapability("build", "build-0003");
        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected void navigateTo(String page) {
        switch (page) {
            case "all products":
                driver.get(BASE_URL + ALL_PRODUCTS);
                break;
            case "shopping cart":
                driver.navigate().to(BASE_URL + SHOPPING_CART);
                break;
            case "home page":
                driver.navigate().to(BASE_URL);
                break;
            default:
                System.err.println("page unrecognized.");
                break;
        }
    }

    /**
     * Stackoverflow: eugene.polschikov http://stackoverflow.com/questions/12858972/how-can-i-ask-the-selenium-webdriver-to-wait-for-few-seconds-in-java
     */
    protected WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        return wait.until(d -> d.findElement(locator));
    }

    /**
     * Stackoverflow: Vivek Jain http://stackoverflow.com/questions/13244225/selenium-how-to-make-the-web-driver-to-wait-for-page-to-refresh-before-executin
     */
    protected void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.assertFalse("Timeout waiting for Page Load Request to complete.", true);
        }
    }

    protected void shutDriverDown() {
        driver.quit();
    }

    protected void waitUntil(Predicate<WebDriver> predicate) {
        wait.until(predicate);
    }
}
