package TestRail;/*
package TestRail;

import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class TestReports {

    private static String URL="https://cyberwoof1.testrail.io/";
    private static String API_KEY="2zabuLNqN3lr2e/EKIh.-asDqZUB1Lv5exxUlazgV";

    public Request createPostRequestToCreateTestCase(){
        MediaType json=MediaType.parse("application/json;charset=utf-8");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put();

        RequestBody requestBody=RequestBody.create(json,jsonObject.toString());
        Request request=new Request.Builder().url(URL).post(requestBody).header("Accept","application/json")
                .header("Authorization", API_KEY).build();
        return request;
    }

    public String getResponseOfTestCaseCreation(){
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=createPostRequestToCreateTestCase();
        Response response= null;
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body().toString();
    }
}
*/
