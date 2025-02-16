package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * MobileTestRunner serves as the main entry point for executing our mobile test scenarios.
 *
 * When you run this class, it does the following:
 * - It instructs JUnit to run tests using Cucumber.
 * - It looks for feature files in "src/test/resources/features".
 * - It scans the "steps" and "hooks" packages for step definitions and hooks.
 * - It outputs the test results in a "pretty" (readable) format in the console.
 * - It uses the default dependency injection provided by PicoContainer.
 *
 * This class does not contain any test logic; it only configures and launches the Cucumber tests.
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
