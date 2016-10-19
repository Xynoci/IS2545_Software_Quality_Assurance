package hw3.entry;

/**
 * Created by Xynoci on 10/16/16.
 */

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by Xynoci on 10/16/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/java/hw3/features",
        glue = {"hw3.steps"},
        plugin = {"html:target/cucumber-html-report"})
//        "json:target/cucumber.json",
//        "pretty:target/cucumber-pretty.txt",
//        "usage:target/cucumber-usage.json",
//        "junit:target/cucumber-result.xml",
//        "com.cucumber.listener.ExtentCucumberFormatter"
public class RunCukesTest {
}
