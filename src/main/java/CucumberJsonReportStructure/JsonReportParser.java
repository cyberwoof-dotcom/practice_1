package CucumberJsonReportStructure;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.*;

public class JsonReportParser {
    private String line;
    private List<Elements> elements;
    private String name;
    private String description;
    private String id;
    private String keyword;
    private String uri;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public List<Elements> getElements() {
        return elements;
    }

    public void setElements(List<Elements> elements) {
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public static List<JsonReportParser> getCucumberReportData() {
        CucumberJsonReportParser cucumberJsonReportParser = new CucumberJsonReportParser();
        List<JsonReportParser> jsonReportParserList = cucumberJsonReportParser.readCucumberJsonReport(JsonReportParser.class);
        return jsonReportParserList;
    }

    public MultiValuedMap<String, String> getAutomationExecutionStatusOfAScenarioOfAFeatureFileForTestRail(String featureFileName, String scenarioName) {
        MultiValuedMap<String, String> multiValueMapOfScenarioAndStatus = new ArrayListValuedHashMap<>();
        String executionStatus = "";
        List<JsonReportParser> parsers = getCucumberReportData();
        String[] featureNamePath=null;
        String[] featureName=null;
        //outerloop:
        for (JsonReportParser parser : parsers) {
            if(parser.getUri().contains("FeatureFiles/")) {
                 featureNamePath = parser.getUri().split("FeatureFiles/");
                 featureName = featureNamePath[1].split(".feature");
            }
            else{
                 featureName = parser.getUri().split(".feature");
            }
            for (Elements element : parser.getElements()) {
                List<String> statusOfEveryStep = new ArrayList<>();
                if (featureFileName.toUpperCase().equals(featureName[0].toUpperCase()) && scenarioName.toUpperCase().contains(element.getName().toUpperCase())) {
                    for (Steps step : element.getSteps()) {
                        if (step.getResult().getStatus().equalsIgnoreCase("Passed") || step.getResult().getStatus().equalsIgnoreCase("failed") || step.getResult().getStatus().equalsIgnoreCase("skipped")) {
                            statusOfEveryStep.add(step.getResult().getStatus());
                        }
                    }
                    if (statusOfEveryStep.size() == element.getSteps().size() && !statusOfEveryStep.contains("failed") && !statusOfEveryStep.contains("undefined") && !statusOfEveryStep.contains("skipped")) {
                        executionStatus = "Passed";
                        multiValueMapOfScenarioAndStatus.put(scenarioName, executionStatus);
                    } else if (statusOfEveryStep.size() == element.getSteps().size() && statusOfEveryStep.contains("failed")) {
                        executionStatus = "Failed";
                        multiValueMapOfScenarioAndStatus.put(scenarioName, executionStatus);
                    } else if (statusOfEveryStep.size() < element.getSteps().size()) {
                        executionStatus = "Failed";
                        multiValueMapOfScenarioAndStatus.put(scenarioName, executionStatus);
                    }
                }
                //break outerloop;
            }
        }

        return multiValueMapOfScenarioAndStatus;
    }

    public List<String> totalScenarioInEveryFeature(String featureFileName){
        Map<String,List<String>> map=new LinkedHashMap<>();
        List<JsonReportParser> parsers = getCucumberReportData();
        List<String>scenarios=new LinkedList<>();
        List<String>totalFeatureFiles=new ArrayList<>();
        String[] featureNamePath=null;
        String[] featureName=null;

        for (int i=0;i<parsers.size();i++) {
            if(parsers.get(i).getUri().contains("FeatureFiles/")) {
                featureNamePath = parsers.get(i).getUri().split("FeatureFiles/");
                featureName = featureNamePath[1].split(".feature");
            }
            else{
                featureName = parsers.get(i).getUri().split(".feature");
            }
            if(featureName[0].equals(featureFileName)){
               for(int j=0;j<parsers.get(i).getElements().size();j++){
                   scenarios.add(parsers.get(i).getElements().get(j).getName());
               }
            }
        }
        return scenarios;
    }

    public static void main(String[] args) {
        JsonReportParser jsonReportParser=new JsonReportParser();
       List<String>list= jsonReportParser.totalScenarioInEveryFeature("SugarCRM_exampleTable");
       System.out.println(list);
    }

}
