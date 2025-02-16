package hooks;

import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * MobileHooks is responsible for initializing and quitting the Appium driver before and after each test scenario.
 * <p>
 * The @Before hook starts the Appium driver, and the @After hook logs the scenario result and quits the driver.
 * </p>
 */
public class MobileHooks {

    private static final Logger logger = LogManager.getLogger(MobileHooks.class);

    /**
     * Executed before each test scenario.
     *
     * @param scenario the current test scenario.
     */
    @Before
    public void setUp(Scenario scenario) {
        logger.info("=== Starting scenario: {} ===", scenario.getName());
        DriverFactory.getDriver();
    }

    /**
     * Executed after each test scenario.
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
        DriverFactory.quitDriver();
    }
}
