package TestRail;

import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRun {

    public String createTestRun(String projectName,String suiteName) throws IOException, APIException {
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
        Map data = new HashMap();
        data.put("suite_id", testSuite.getTestSuiteId(projectName,suiteName));
        data.put("name", "CMP_TRIAL_AUTOMATION_TEST_RUN");
        data.put("description", "This is a test run instance");
        data.put("milestone_id", "This test worked fine!");
        data.put("assignedto_id", "This test worked fine!");
        data.put("include_all", true);
        data.put("case_ids", "This test worked fine!");
        data.put("refs", "This test worked fine!");

        JSONObject jsonObject = (JSONObject) client.sendPost("add_run/"+testProjects.getAllProjects(projectName),data);
        return testCaseIds;
    }

    public String getTestRun(String projectName,String testRunName) throws IOException, APIException {
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
            if (jsonObject.get("name").equals(testRunName)) {
                testRunIds = jsonObject.get("id").toString();
            }
        }
        return testRunIds;
    }
}
