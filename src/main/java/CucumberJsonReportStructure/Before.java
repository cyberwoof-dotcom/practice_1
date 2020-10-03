package CucumberJsonReportStructure;

public class Before {
    private Result result;

    private Match match;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Before [result = " + result + ", match = " + match + "]";
    }
}
