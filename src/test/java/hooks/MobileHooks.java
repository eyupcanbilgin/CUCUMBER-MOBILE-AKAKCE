package hooks;

import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MobileHooks is responsible for initializing and quitting the Appium driver before and after each test scenario.
 *
 * <p>
 * In the @Before hook, it starts the Appium driver (via DriverFactory) so that the mobile application is ready for testing.
 * In the @After hook, it logs the scenario outcome (passed or failed) and shuts down the driver to clean up resources.
 * </p>
 */
public class MobileHooks {

    private static final Logger logger = LogManager.getLogger(MobileHooks.class);

    /**
     * This method is executed before each test scenario.
     * It logs the start of the scenario and initializes the Appium driver.
     *
     * @param scenario the current test scenario.
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("=== Starting scenario: {} ===", scenario.getName());
        // Initialize the Appium driver
        DriverFactory.getDriver();
    }

    /**
     * This method is executed after each test scenario.
     * It logs whether the scenario passed or failed and then quits the Appium driver.
     *
     * @param scenario the test scenario that just finished.
     */
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            logger.error("=== Scenario FAILED: {} ===", scenario.getName());
        } else {
            logger.info("=== Scenario PASSED: {} ===", scenario.getName());
        }
        // Quit the Appium driver to release resources
        DriverFactory.quitDriver();
    }
}
