package Enums;

public enum Constants {

    APPLICATION_URL("https://qlsugqa01.cmpa.org/"),
    MEMBER_EVENTS_URL("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents"),
    MEMBER_EVENTS_CREATE_URL("https://qlsugqa01.cmpa.org/#CM_EV_MemberEvents/create"),
    TEST_RAIL_URL("http://testrail.cmpa.org:8000/"),
    TEST_RAIL_PROJECT_NAME("TestRail Test Project Space (i.e. SANDBOX)"),
    TEST_RAIL_SUITE_NAME("CMP_Test_Features"),
    CHROMEDRIVER_EXECUTABLE_PATH(".//chromedriver.exe"),
    phantomJSExecutablePath(".//phantomjs.exe"),
    CSVJSONURL("https://www.seleniumeasy.com/test/input-form-demo.html");

    public String value;

    Constants(String value){
        this.value=value;
    }
}
