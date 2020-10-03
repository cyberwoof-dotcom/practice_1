package CucumberJsonReportStructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CucumberJsonReportParser {

    private static String cucumberJsonReportPath = ".//target//cucumber.json";

    public <T> List<T> readCucumberJsonReport(Class<T> clazz) {
        List<T> list = new ArrayList<>();
        String[] finalValue = null;
        JsonNode node = null;
        Path resourceDirectory = null;
        ObjectMapper mapper = new ObjectMapper();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals("getCucumberReportData")) {
                method.setAccessible(true);
                try {
                    String[] value = clazz.getSimpleName().split("\\.");
                    System.out.println(value[0]);
                    resourceDirectory = Paths.get(cucumberJsonReportPath);
                    CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
                    list = mapper.readValue(resourceDirectory.toFile(), type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
