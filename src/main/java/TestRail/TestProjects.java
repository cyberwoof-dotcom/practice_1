package TestRail;

import Enums.Constants;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestProjects {

    public static void main(String[] args) throws IOException, APIException {
        TestProjects testProjects = new TestProjects();
        System.out.println(testProjects.getAllProjects("CYB_TRIAL_AUTOMATION"));
    }

    public String getAllProjects(String projectName) throws IOException, APIException {
        ReadProperties properties = new ReadProperties();
        String projectId = "";
        APIClient client = new APIClient(Constants.TEST_RAIL_URL.value);
        Map<String, String> mapOfCredentials = properties.propertyFileRead();
        for (Map.Entry<String, String> map : mapOfCredentials.entrySet()) {
            client.setUser(map.getKey());
            client.setPassword(map.getValue());
        }
        List<JSONObject> c = (List<JSONObject>) client.sendGet("get_projects");
        for (JSONObject jsonObject : c) {
            if (jsonObject.get("name").equals(projectName)) {
                projectId = jsonObject.get("id").toString();
            }
        }
        return projectId;
    }
}
