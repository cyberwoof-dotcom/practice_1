package TestRail;

import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCase {

    public static void main(String[] args) throws IOException, APIException {
        TestCase testcase = new TestCase();
        List<String>testCaseNames=testcase.getAllTestCaseNames();
        System.out.println(testCaseNames);
      //  System.out.println(testcase.getTestCaseIds("CYB_TRIAL_AUTOMATION","","Login to Auctiva"));
    }

    public String getTestCaseIds(String projectName,String suiteName,String testCaseName) throws IOException, APIException {
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects=new TestProjects();
        TestSuite testSuite=new TestSuite();
        String testCaseIds= "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_cases/"+testProjects.getAllProjects(projectName)+"&suite_id="+testSuite.getTestSuiteId(projectName,suiteName)+"");
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("title").equals(testCaseName)) {
                testCaseIds = jsonObject.get("id").toString();
            }
        }
        return testCaseIds;
    }

    public List<String> getAllTestCaseNames() throws IOException, APIException {
        List<String>listOfTestCaseNames=new ArrayList<>();
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects=new TestProjects();
        TestSuite testSuite=new TestSuite();
        String testCaseIds= "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_cases/"+testProjects.getAllProjects(Constants.TEST_RAIL_PROJECT_NAME.value)+"&suite_id="+testSuite.getTestSuiteId(Constants.TEST_RAIL_PROJECT_NAME.value,Constants.TEST_RAIL_SUITE_NAME.value)+"");
        for (JSONObject jsonObject : c) {
            listOfTestCaseNames.add(jsonObject.get("title").toString());
        }
        return listOfTestCaseNames;
    }
}
