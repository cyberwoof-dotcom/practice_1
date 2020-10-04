package CucumberJsonReportStructure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {
    private String location;
    private List<Arguments> arguments;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Arguments> getArguments() {
        return arguments;
    }

    public void setArguments(List<Arguments> arguments) {
        this.arguments = arguments;
    }
}
