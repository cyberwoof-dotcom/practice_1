Feature: Data Validation of Sugar CRM
Description: The purpose of this feature file is to validate data on a SugarCRM screen

Scenario: Extracting User Data from the Screen 
Given I navigate to the SugarCRM Login Page
When I submit my User Name and Password
Then I should be directed to the SugarCRM Main Page
And I select the Member Files Menu
And I enter a File Number 
And I select the File
And I extract and print the File Name
And I extract and print the Clinical Summary
And I extract and print the Assigned to
And I extract Verify Status
And I extract and print the DOO
And I extract and print the COO
And I extract and print the POO
Then I will mark this test as Complete