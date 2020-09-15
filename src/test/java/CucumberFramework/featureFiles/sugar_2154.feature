Feature: Verify Features Added to Member Event Module

Scenario Outline:  Validate updates to a Member Event Module
Given I navigate to the SugarCRM QA Website
When I login with my username <username> and password <password>
And I select the Member Events Subpanel
And I select the Create button
And I should be able to select the Event Type <eventtype> End Membership
And I should be able to select the <reason> Reason
And I should be able to select an <effdate> Effective Date
And I should be able to select a <member> Member
Then I should be able to select the Save button and Save the data
And I will logout of SugarCRM

Examples:
|username    |password       |eventtype        |reason         |effdate          |member       |
|"Admin"     |"Password123"  |"End Membership" |"No Licensure" |"09/11/2020"     |"John Cleese"|
