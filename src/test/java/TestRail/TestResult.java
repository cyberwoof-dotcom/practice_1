package TestRail;


import Enums.Constants;
import TestNGReport.TestNGReportReadAndParse;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestResult {

    public String populateTestResult(String projectName,String suiteName,String testCaseName,String testRunName) throws Exception {
        ReadProperties properties = new ReadProperties();
        String automationStatus="";
        String statusToPopulateinTestRail="";
        TestNGReportReadAndParse testNGReportReadAndParse=new TestNGReportReadAndParse();
        Map<String,String> automationStatusMap=testNGReportReadAndParse.readTestNGReport();
        for(Map.Entry<String,String>entry:automationStatusMap.entrySet()){
            automationStatus=entry.getValue();
        }
        if(automationStatus.equalsIgnoreCase("PASS")){
            statusToPopulateinTestRail="1";
        }
        else if(automationStatus.equalsIgnoreCase("FAIL")){
            statusToPopulateinTestRail="5";
        }
        TestCase testCase=new TestCase();
        TestRun testRun=new TestRun();
        String testCaseIds= "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        Map data = new HashMap();
        data.put("status_id", statusToPopulateinTestRail);
        data.put("comment", "CMP_TRIAL_AUTOMATION_TEST_Result");
        data.put("version", "");
        data.put("elapsed", "");
        data.put("defects", "");
        data.put("assignedto_id", 1);

        JSONObject jsonObject = (JSONObject) client.sendPost("add_result_for_case/"+testRun.getTestRun(projectName,testRunName)+"/"+testCase.getTestCaseIds(projectName,suiteName,testCaseName),data);
        return testCaseIds;
    }

    public String getTestResult(String projectName) throws IOException, APIException {
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects = new TestProjects();
        TestSuite testSuite = new TestSuite();
        String testRunIds = "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<JSONObject> c = (List<JSONObject>) client.sendGet("get_runs/"+testProjects.getAllProjects(projectName));
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("name").equals("Auctiva Login-Run")) {
                testRunIds = jsonObject.get("id").toString();
            }
        }
        return testRunIds;
    }
}
