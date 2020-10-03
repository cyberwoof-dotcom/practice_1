package TestRail;

import CucumberJsonReportStructure.JsonReportParser;
import Enums.Constants;
import org.apache.commons.collections4.MultiValuedMap;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestResult {
    public static void main(String[] args) throws Exception {
        TestResult testResult = new TestResult();
        testResult.populateTestResult();
    }

    public void populateTestResult() throws Exception {
        ReadProperties properties = new ReadProperties();
        String automationStatus = "";
        String statusToPopulateinTestRail = "";

        JsonReportParser jsonReportParser = new JsonReportParser();
        TestCase testCase = new TestCase();
        TestSuite testSuite = new TestSuite();
        TestRun testRun=new TestRun();
        for (String testSuiteName : testSuite.getAllTestSuiteName(Constants.TEST_RAIL_PROJECT_NAME.value)) {
            String testRunInstance=testRun.createTestRun(Constants.TEST_RAIL_PROJECT_NAME.value,testSuiteName);
            List<String> listOfScenariosOfAFeature = jsonReportParser.totalScenarioInEveryFeature(testSuiteName);
            for (String scenario : listOfScenariosOfAFeature) {
                MultiValuedMap<String, String> multiMap = jsonReportParser.getAutomationExecutionStatusOfAScenarioOfAFeatureFileForTestRail(testSuiteName, scenario);

                for (Map.Entry<String, String> entry : multiMap.entries()) {
                    automationStatus = entry.getValue();
                }
                if (automationStatus.equalsIgnoreCase("passed")) {
                    statusToPopulateinTestRail = "1";
                } else if (automationStatus.equalsIgnoreCase("failed")) {
                    statusToPopulateinTestRail = "5";
                }

                APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
                Map<String, String> mapOfCredentials = properties.propertyFileRead();
                for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
                    client.setUser(map.getKey());
                    client.setPassword(map.getValue());
                }
                Map data = new HashMap();
                data.put("status_id", statusToPopulateinTestRail);
                data.put("comment", "CYB_TRIAL_AUTOMATION_TEST_Result");
                data.put("version", "");
                data.put("elapsed", "");
                data.put("defects", "");
                data.put("assignedto_id", 1);

                JSONObject jsonObject = (JSONObject) client.sendPost("add_result_for_case/" + testRunInstance + "/" + testCase.getTestCaseIds(Constants.TEST_RAIL_PROJECT_NAME.value, testSuiteName, scenario), data);
            }
        }
    }

    public String getTestResult(String projectName) throws IOException, APIException {
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects = new TestProjects();
        TestSuite testSuite = new TestSuite();
        String testRunIds = "";
        APIClient client = new APIClient("https://cyberwoof1.testrail.io/");
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_runs/" + testProjects.getAllProjects(projectName));
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("name").equals("Auctiva Login-Run")) {
                testRunIds = jsonObject.get("id").toString();
            }
        }
        return testRunIds;
    }


}
