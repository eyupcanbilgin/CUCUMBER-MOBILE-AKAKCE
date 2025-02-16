package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * MobileTestRunner is the main entry point for executing mobile test scenarios using Cucumber.
 * It scans for feature files and step definitions, and outputs test results in a readable format.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps", "hooks"},
        plugin = {"pretty"},
        monochrome = true
)
public class MobileTestRunner {
    // No additional code is needed as the annotations configure the test runner.
}
