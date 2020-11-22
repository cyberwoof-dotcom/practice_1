@sugar_0001
Feature: End to End Testing Module 

Scenario Outline:  Create a Member File
Given user navigates to the SugarCRM QA Website
When user logs in with username <username> and password <password>
And user navigates to Members Search Page
And user searches for a <memberName> Member
And user selects the member to process using <memberid> member id
And user creates a new file using the plus on the file panel
And enters the <clinicalSummary> Clinical Summary Field
And enters the <assignedTo> Assigned To Field
And enters the <specialIssues> Special Issue Field
And saves the record 
Then the system displays a Confirmation Message with a new File Link
And Creates the File
And Returns the user to the Member Record

Examples:
|username    |password       |memberName    |memberid | clinicalsummary|specialissue|assignto        |
|"admin"     |"Password123"  |"Yoskovitch"  |"999999" | automate..     |Cosmetics   |Yolanda Madarnas|