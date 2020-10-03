package CucumberJsonReportStructure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestNGReportReadAndParse {

    public Map<String,String> readTestNGReport() throws ParserConfigurationException, IOException, SAXException {

        Map<String,String>mapOfStatus=new HashMap<String, String>();
        String path = System.getProperty("user.dir")+"/test-output/testng-results.xml";
        File fXmlFile = new File(path);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Total : " + doc.getDocumentElement().getAttribute("total")) ;
        System.out.println("Passed : " +doc.getDocumentElement().getAttribute("passed")) ;
        System.out.println("Failed : " +doc.getDocumentElement().getAttribute("failed")) ;
        System.out.println("Skipped : " +doc.getDocumentElement().getAttribute("skipped")) ;

        NodeList tMethods = doc.getElementsByTagName("test-method");

        for (int temp = 0; temp < tMethods.getLength(); temp++){
            Node node = tMethods.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element eElement = (Element) node;
                // Get parent element to capture suite name
                Element suiteElement = (Element) eElement.getParentNode();
                System.out.println("Suite: "    + suiteElement.getAttribute("name"));
                // Get test case name
                System.out.println("Name: "    + eElement.getAttribute("name"));
                // Get test case status
                System.out.println("Status: "    + eElement.getAttribute("status"));
                // Get test case duration
                System.out.println("Duration: "    + eElement.getAttribute("duration-ms"));
                // Get exception message if exist
                Node eNode = eElement.getElementsByTagName("exception").item(0);
                Element exceptionNode = (Element) eNode;
                if (exceptionNode != null) {
                    System.out.println("Error Message: "    + exceptionNode.getAttribute("class"));
                } else {
                    System.out.println("Error Message: ");
                }
              mapOfStatus.put(eElement.getAttribute("name"),eElement.getAttribute("status"));
            }
        }
        return mapOfStatus;
    }

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        TestNGReportReadAndParse testNGReportReadAndParse=new TestNGReportReadAndParse();
        Map<String,String>map=testNGReportReadAndParse.readTestNGReport();
        System.out.println(map);
    }
}
