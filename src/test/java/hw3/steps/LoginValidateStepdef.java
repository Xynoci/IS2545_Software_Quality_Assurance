package hw3.steps;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.AssumptionViolatedException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import cucumber.api.java8.En;
import hw3.base.BaseGherkin;

/**
 * Created by Xynoci on 10/18/16.
 */
public class LoginValidateStepdef extends BaseGherkin implements En {
    public LoginValidateStepdef() {
        Given("^I'm about to \"([^\"]*)\"$", (String login) -> {
            super.initDriver(super.DEFAULT_DRIVER);
            switch (login) {
                case "login":
                    driver.get(BASE_URL + LOG_IN);
                    break;
                default:
                    System.err.println("page unrecognized.");
                    break;
            }
        });

        When("^I try to log in using \"([^\"]*)\" and \"([^\"]*)\"$", (String username, String password) -> {
            try {
                String errorMessageOnPage = driver.findElement(By.id("login_error")).getText();
                throw new AssumptionViolatedException("Error on page: \n" + errorMessageOnPage);
            } catch (AssumptionViolatedException e) {
                Assume.assumeNoException(e);
            } catch (NoSuchElementException e) {
                driver.findElement(By.id("user_login")).sendKeys(username);
                driver.findElement(By.id("user_pass")).sendKeys(password);
                driver.findElement(By.id("loginform")).submit();
            }
        });

        Then("^I should see whether they are \"([^\"]*)\" or not$", (String validator) -> {
            switch (validator) {
                case "invalid":
                    String expectedErrorMessage = "ERROR:Invalidlogincredentials.";
                    waitUntil(d -> (d.findElement(By.id("login_error")) != null));
                    String errorMessage = driver.findElement(By.id("login_error")).getText(),
                            processedErrorMessage = errorMessage.replaceAll("\\s+", "");
                    Assert.assertEquals(expectedErrorMessage, processedErrorMessage);
                    break;
                case "valid":
                    String expectedSuffix = "wp-admin/profile.php";
                    waitUntil(d -> d.getCurrentUrl().endsWith(expectedSuffix));
                    Assert.assertTrue(driver.getCurrentUrl().endsWith(expectedSuffix));
                    break;
                default:
                    System.err.println("LoginValidateStepdef: unexpected status.");
                    break;
            }
            shutDriverDown();
        });

    }
}
