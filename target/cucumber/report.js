$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("sugar_2154.feature");
formatter.feature({
  "line": 2,
  "name": "Verify Features Added to Member Event Module",
  "description": "",
  "id": "verify-features-added-to-member-event-module",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@sugar_2154"
    }
  ]
});
formatter.scenarioOutline({
  "line": 4,
  "name": "Validate updates to a Member Event Module",
  "description": "",
  "id": "verify-features-added-to-member-event-module;validate-updates-to-a-member-event-module",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 5,
  "name": "I navigate to the SugarCRM QA Website",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I login with my username \u003cusername\u003e and password \u003cpassword\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I select the Member Events Subpanel",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I select the Create button",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I should be able to select the Event Type \u003ceventtype\u003e End Membership",
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I should be able to select the \u003creason\u003e Reason",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I should be able to select an \u003ceffdate\u003e Effective Date",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I should be able to select a \u003cmember\u003e Member",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I should be able to select the Save button and Save the data",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I will logout of SugarCRM",
  "keyword": "And "
});
formatter.examples({
  "line": 16,
  "name": "",
  "description": "",
  "id": "verify-features-added-to-member-event-module;validate-updates-to-a-member-event-module;",
  "rows": [
    {
      "cells": [
        "username",
        "password",
        "eventtype",
        "reason",
        "effdate",
        "member"
      ],
      "line": 17,
      "id": "verify-features-added-to-member-event-module;validate-updates-to-a-member-event-module;;1"
    },
    {
      "cells": [
        "\"Admin\"",
        "\"Password123\"",
        "\"End Membership\"",
        "\"No Licensure\"",
        "\"09/11/2020\"",
        "\"John Cleese\""
      ],
      "line": 18,
      "id": "verify-features-added-to-member-event-module;validate-updates-to-a-member-event-module;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 4502826800,
  "status": "passed"
});
formatter.before({
  "duration": 3252609500,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "Validate updates to a Member Event Module",
  "description": "",
  "id": "verify-features-added-to-member-event-module;validate-updates-to-a-member-event-module;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@sugar_2154"
    }
  ]
});
formatter.step({
  "line": 5,
  "name": "I navigate to the SugarCRM QA Website",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I login with my username \"Admin\" and password \"Password123\"",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "I select the Member Events Subpanel",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I select the Create button",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I should be able to select the Event Type \"End Membership\" End Membership",
  "matchedColumns": [
    2
  ],
  "keyword": "And "
});
formatter.step({
  "line": 10,
  "name": "I should be able to select the \"No Licensure\" Reason",
  "matchedColumns": [
    3
  ],
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I should be able to select an \"09/11/2020\" Effective Date",
  "matchedColumns": [
    4
  ],
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "I should be able to select a \"John Cleese\" Member",
  "matchedColumns": [
    5
  ],
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I should be able to select the Save button and Save the data",
  "keyword": "Then "
});
formatter.step({
  "line": 14,
  "name": "I will logout of SugarCRM",
  "keyword": "And "
});
formatter.match({
  "location": "sugar_2154.i_navigate_to_the_SugarCRM_QA_Website()"
});
formatter.result({
  "duration": 1354359000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Admin",
      "offset": 26
    },
    {
      "val": "Password123",
      "offset": 47
    }
  ],
  "location": "sugar_2154.i_login_with_my_username_and_password(String,String)"
});
formatter.result({
  "duration": 1421306700,
  "status": "passed"
});
formatter.match({
  "location": "sugar_2154.i_select_the_Member_Events_Subpanel()"
});
formatter.result({
  "duration": 54409800,
  "status": "passed"
});
formatter.match({
  "location": "sugar_2154.i_select_the_Create_button()"
});
formatter.result({
  "duration": 40875200,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "End Membership",
      "offset": 43
    }
  ],
  "location": "sugar_2154.i_should_be_able_to_select_the_Event_Type_End_Membership(String)"
});
formatter.result({
  "duration": 4092393400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "No Licensure",
      "offset": 32
    }
  ],
  "location": "sugar_2154.i_should_be_able_to_select_the_Reason(String)"
});
formatter.result({
  "duration": 2187261800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "09/11/2020",
      "offset": 31
    }
  ],
  "location": "sugar_2154.i_should_be_able_to_select_an_Effective_Date(String)"
});
formatter.result({
  "duration": 1323979100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "John Cleese",
      "offset": 30
    }
  ],
  "location": "sugar_2154.i_should_be_able_to_select_a_Member(String)"
});
formatter.result({
  "duration": 2295892100,
  "status": "passed"
});
formatter.match({
  "location": "sugar_2154.i_should_be_able_to_select_the_Save_button_and_Save_the_data()"
});
formatter.result({
  "duration": 620556500,
  "status": "passed"
});
formatter.match({
  "location": "sugar_2154.i_will_logout_of_SugarCRM()"
});
formatter.result({
  "duration": 2149949000,
  "status": "passed"
});
});