package TestRail;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadProperties {
    InputStream inputStream;
    String userName="";
    String password="";

    public Map<String,String> propertyFileRead() throws IOException {
        Map<String,String>map=new HashMap<String, String>();
        Properties properties=new Properties();
        inputStream=getClass().getClassLoader().getResourceAsStream("TestRail.properties");
        if(inputStream!=null){
            properties.load(inputStream);
        }
        userName=properties.getProperty("Username");
        password=properties.getProperty("Password");
        map.put(userName,password);
        return map;
    }
}
