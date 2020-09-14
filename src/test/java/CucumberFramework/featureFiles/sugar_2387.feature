@sugar_2387
Feature: For each new Mother Module created, the name field is auto-populated with 
the Case Interested Party and an incremental number will be generated for each new 
record

Scenario Outline:  Creating a Mother Record
Given I navigate to Sugar CRM
When I login using my userid <userid> and password <password>
And I select Medical Coding
And I enter a Case ID <caseid>
Then I should be able to create a Mother record and verify the incremental number and interested party
And I Logout of Sugar CRM

Examples:
|userid       |password      |caseid      |
|"admin"      |"Password123" |"348482LL"  |
|"admin"      |"Password123" |"1022145LL" |