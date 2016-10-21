package hw3.gherkins;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by Xynoci on 10/16/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "src/test/resources/hw3/gherkins",
        glue = {"hw3.gherkins"},
        plugin = {"html:target/cucumber-html-report"})
//        "json:target/cucumber.json",
//        "pretty:target/cucumber-pretty.txt",
//        "usage:target/cucumber-usage.json",
//        "junit:target/cucumber-result.xml",
//        "com.cucumber.listener.ExtentCucumberFormatter"
public class RunCukesTest {
}
