package TestRail;

import Context.ThreadContextForScenarios;
import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TestRun {

    public static void main(String[] args) throws IOException, APIException {
        TestRun TestRun = new TestRun();
      /*  TestRun.createTestRun(Constants.TEST_RAIL_PROJECT_NAME.value, "SugarCRM_exampleTable");
        System.out.println(TestRun.getTestRun(Constants.TEST_RAIL_PROJECT_NAME.value));*/
    }

    public String createTestRun(String projectName,String suiteName) throws IOException, APIException {
        ThreadContextForScenarios.getInstance();
        ReadProperties properties = new ReadProperties();
        TestProjects testProjects = new TestProjects();
        String testRunId = "";
        TestSuite testSuite = new TestSuite();
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        Map data = new HashMap();
        data.put("suite_id", testSuite.getTestSuiteId(projectName, suiteName));
        String testRun = "";
        testRun = "CYB_TRIAL_AUTOMATION_TEST_RUN" + "-"+suiteName +"-------" +LocalDateTime.now();
        data.put("name", testRun);
        System.out.println("testRunName-" + testRun);
        ThreadContextForScenarios.setScenarioContext("name", testRun);
        data.put("description", "This is a test run instance");
        data.put("milestone_id", "");
        data.put("assignedto_id", "");
        data.put("include_all", true);
        data.put("case_ids", "");
        data.put("refs", "");

        JSONObject jsonObject = (JSONObject) client.sendPost("add_run/" + testProjects.getAllProjects(projectName), data);
        List<JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_runs/" + testProjects.getAllProjects(projectName));
        for (JSONObject jsonObj : c) {
            if (jsonObj.get("name").equals(testRun)) {
                testRunId = jsonObject.get("id").toString();
            }
        }
        return testRunId;
    }

    public String getTestRun(String projectName) throws IOException, APIException {
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
        List<JSONObject> c = (List<org.json.simple.JSONObject>) client.sendGet("get_runs/" + testProjects.getAllProjects(projectName));
        System.out.println("testRunName-" + ThreadContextForScenarios.getScenarioContext("name"));
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("name").equals(ThreadContextForScenarios.getScenarioContext("name"))) {
                testRunIds = jsonObject.get("id").toString();
            }
        }
        return testRunIds;
    }

    public String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }
}
