# Akakçe Mobile Test Automation

## Overview
This project automates mobile testing for the Akakçe Android application using Appium and Cucumber. The automation tests cover searching for a laptop, applying category filters, sorting by the highest price, and verifying navigation to the seller's page.

## Technologies Used
- **Java 21**
- **Appium (Java Client 7.6.0)**
- **Cucumber (7.11.0)**
- **JUnit (4.13.2)**
- **Log4j2**
- **Maven**

## Project Structure
```
MobileTestAutomation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── drivers/          # Appium driver setup
│   │   │   ├── pages/            # Page Object Model classes
│   │   │   ├── hooks/            # Cucumber hooks
│   │   │   ├── steps/            # Step definitions
│   │   │   ├── runners/          # Test runner
│   │   │   ├── utils/            # Utility classes (if needed)
│   ├── test/
│   │   ├── resources/
│   │   │   ├── features/        # Cucumber feature files
│   ├── pom.xml                  # Maven dependencies
```

## Setup & Installation

### Prerequisites
Ensure that you have the following installed:
- **Java 21**
- **Android SDK & Emulator / Physical Device**
- **Appium Server**
- **Maven**

### Steps to Set Up
1. Clone the repository:
   ```sh
   git clone <eyupcanbilgin>
   cd MobileTestAutomation
   ```
2. Install dependencies using Maven:
   ```sh
   mvn clean install
   ```
3. Start Appium server:
   ```sh
   appium --log-level error
   ```
4. Connect an Android device or start an emulator.

## Running Tests
To execute the mobile tests, use the following command:
```sh
mvn test
```

### Running Specific Tests
You can run a specific feature file using:
```sh
mvn test -Dcucumber.features=src/test/resources/features/laptopSearch.feature
```

## Test Scenario
### Feature: Mobile App Laptop Search and Filter
```
As a mobile app user,
I want to search for a laptop, apply the appropriate filters, and sort by highest price,
So that I can view detailed product information and navigate to the seller's page.

Scenario: Search for a laptop, filter by category, sort by highest price, and navigate to product details
    Given I launch the application as a guest user
    When I enter "Laptop" into the search field and press Enter
    And I tap the filter button
    And I select the "Bilgisayar ve Donanım" category
    And I tap the "View Products" button
    And I tap the sort button
    And I select the "Highest Price" option
    And I tap on the 10th unique product in the list
    And I tap the "Go to Product" button
    Then I should see the "Go to Seller" button
```

## Project Components

### 1. **Driver Factory (`DriverFactory.java`)**
- Initializes and manages the Appium driver.
- Uses **AndroidDriver** with **UiAutomator2**.
- Sets capabilities for the Akakçe app.

### 2. **Hooks (`MobileHooks.java`)**
- Handles test setup (`@Before`) and teardown (`@After`).
- Logs test results.

### 3. **Page Object Model (`HomePage.java`)**
- Implements interactions with UI elements:
    - Search for products.
    - Apply filters and sorting.
    - Select a product.
    - Navigate to the seller page.

### 4. **Step Definitions (`HomeSteps.java`)**
- Implements the Cucumber steps for the feature file.

### 5. **Test Runner (`MobileTestRunner.java`)**
- Runs Cucumber scenarios using JUnit.

## Troubleshooting
- If the driver does not start, ensure the Appium server is running.
- If tests fail due to element visibility, increase the wait time in `WebDriverWait`.
- Verify that the Akakçe app is installed on the device.

## Contributing
1. Fork the repository.
2. Create a feature branch (`git checkout -b feature-branch`).
3. Commit changes (`git commit -m "Add new feature"`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

