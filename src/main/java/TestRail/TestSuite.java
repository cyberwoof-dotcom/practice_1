package TestRail;

import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestSuite {
    public static void main(String[] args) throws IOException, APIException {
        TestSuite testsuite = new TestSuite();
        System.out.println(testsuite.getAllTestSuiteName(Constants.TEST_RAIL_PROJECT_NAME.value));
      //  System.out.println(testsuite.getTestSuiteId("CYB_TRIAL_AUTOMATION",""));
    }

    public String getTestSuiteId(String projectName,String suiteName) throws IOException, APIException {
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects=new TestProjects();
        String testSuiteId = "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<org.json.simple.JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_suites/"+testProjects.getAllProjects(projectName)+"");
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("name").equals(suiteName)) {
                testSuiteId = jsonObject.get("id").toString();
            }
        }
        return testSuiteId;
    }

    public List<String> getAllTestSuiteName(String projectName) throws IOException, APIException {
        List<String>listOfTestSuite=new LinkedList<>();
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects=new TestProjects();
        String testSuiteId = "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<org.json.simple.JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_suites/"+testProjects.getAllProjects(projectName)+"");
        for (JSONObject jsonObject : c) {
            listOfTestSuite.add(jsonObject.get("name").toString());
        }
        return listOfTestSuite;
    }
}
