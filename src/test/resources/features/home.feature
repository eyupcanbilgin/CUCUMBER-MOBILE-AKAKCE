Feature: Mobile App Laptop Search and Filter
  As a mobile app user
  I want to search for a laptop, apply the appropriate filters, and sort by highest price
  So that I can view detailed product information and navigate to the seller's page

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
