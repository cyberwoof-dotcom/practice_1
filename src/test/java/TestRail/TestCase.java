package TestRail;

import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestCase {

    public static void main(String[] args) throws IOException, APIException {
        TestCase testcase = new TestCase();
        System.out.println(testcase.getTestCaseIds("CYB_TRIAL_AUTOMATION","","Login to Auctiva"));
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
        List<JSONObject> c = (List<JSONObject>) client.sendGet("get_cases/"+testProjects.getAllProjects(projectName)+"&suite_id="+testSuite.getTestSuiteId(projectName,suiteName)+"");
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("title").equals(testCaseName)) {
                testCaseIds = jsonObject.get("id").toString();
            }
        }
        return testCaseIds;
    }
}
