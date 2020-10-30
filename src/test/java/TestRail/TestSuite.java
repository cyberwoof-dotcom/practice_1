package TestRail;

import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestSuite {
    public static void main(String[] args) throws IOException, APIException {
        TestSuite testsuite = new TestSuite();
        System.out.println(testsuite.getTestSuiteId("CMP_TRIAL_AUTOMATION",""));
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
        List<JSONObject> c = (List<JSONObject>) client.sendGet("get_suites/"+testProjects.getAllProjects(projectName)+"");
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("name").equals(suiteName)) {
                testSuiteId = jsonObject.get("id").toString();
            }
        }
        return testSuiteId;
    }
}
