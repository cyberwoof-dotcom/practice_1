package TestRail;


import Context.ThreadContextForScenarios;
import Enums.Constants;
import TestNGReport.TestNGReportReadAndParse;
import org.apache.commons.collections4.MultiValuedMap;
import org.json.simple.JSONObject;
import utilities.FileParser;
import utilities.TxtReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TestResult {

    public String populateTestResult(String projectName,String suiteName,String testCaseName,String testRunName,String methodName,int counter) throws Exception {
        int count=0;
        ReadProperties properties = new ReadProperties();
        String automationStatus="";
        String statusToPopulateinTestRail="";
        TestNGReportReadAndParse testNGReportReadAndParse=new TestNGReportReadAndParse();
        MultiValuedMap<String,String> automationStatusMap=testNGReportReadAndParse.readTestNGReport();
       /* for(Map.Entry<String,String>entry:automationStatusMap.entries()){
            automationStatus=entry.getValue();
        }*/

        for(Map.Entry<String,String>map:automationStatusMap.entries()) {

                if(map.getKey().equalsIgnoreCase(methodName)) {
                    /*System.out.println("Inside Test result of test rail for method-------" +methodName+" --------and counter is---- "+count);
                    automationStatus = automationStatusMap.entries().stream().skip(count).findFirst().get().getValue();
                    System.out.println("Inside Test result of test rail for method-------" +methodName+" --------and counter is---- "+count+"-----and automation status is ----"+automationStatus);*/
                    if (count == counter) {
                        System.out.println(map.getValue());
                        automationStatus=map.getValue();
                    }
                    count++;

                }

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

        String comment="";
        FileParser fileParser=new FileParser();
        Map<String,List<String>>map=fileParser.fileToMapConversionForTestRail();

        for(Map.Entry<String,List<String>>entry:map.entrySet()){
        	if(testCaseName.contains(entry.getKey())) {
                StringBuilder stringBuilder=new StringBuilder();
                for(String value:entry.getValue()){
                    stringBuilder.append(value+" \n");
                }
                comment=stringBuilder.toString();
            }
        }

        Map data = new HashMap();
        data.put("status_id", statusToPopulateinTestRail);
        data.put("comment", comment);
        data.put("version", "");
        data.put("elapsed", "");
        data.put("defects", "");
        data.put("assignedto_id", 1);
        data.put("custom_testing_environment", 1);

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
