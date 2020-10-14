package Beans;

import Annotation.CSVAnnotation;
import Annotation.CSVProviderAnnotation;
import Annotation.JsonAnnotation;
import Annotation.JsonProviderAnnotation;
import Utilities.CSVUtility;
import Utilities.JsonUtility;

import java.util.List;

@JsonAnnotation
@CSVAnnotation
public class Sugar9999 {
    private String fileNumber;
    private String assignedTo;
    private String status;
    private String dateOfOccurrence;
    private String poo_Code;
    private String poo_Name;

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfOccurrence() {
        return dateOfOccurrence;
    }

    public void setDateOfOccurrence(String dateOfOccurrence) {
        this.dateOfOccurrence = dateOfOccurrence;
    }

    public String getPoo_Code() {
        return poo_Code;
    }

    public void setPoo_Code(String poo_Code) {
        this.poo_Code = poo_Code;
    }

    public String getPoo_Name() {
        return poo_Name;
    }

    public void setPoo_Name(String poo_Name) {
        this.poo_Name = poo_Name;
    }

    @CSVProviderAnnotation(value="simpleReadWithOneRowinCSV")
    public static Sugar9999 getCSVDataSimple(){
        CSVUtility csvUtility=new CSVUtility();
        Sugar9999 sugar9999= (Sugar9999) csvUtility.csvRead(Sugar9999.class);
        return sugar9999;
    }

    @CSVProviderAnnotation(value="readCSVWithUniqueIdentfierFromFeatureFile")
    public static Sugar9999 getCSVData(String value){
        CSVUtility csvUtility=new CSVUtility();
        List<Sugar9999> inputFormList=csvUtility.csvReadAll(Sugar9999.class);
        Sugar9999 inputForm=new Sugar9999();
        for(Sugar9999 form:inputFormList){
            if(form.getFileNumber().equalsIgnoreCase(value)){
                inputForm.setFileNumber(form.getFileNumber());
                inputForm.setAssignedTo(form.getAssignedTo());
                inputForm.setDateOfOccurrence(form.getDateOfOccurrence());
                inputForm.setStatus(form.getStatus());
                inputForm.setPoo_Code(form.getPoo_Code());
                inputForm.setPoo_Name(form.getPoo_Name());
            }
        }
        return inputForm;
    }

    @JsonProviderAnnotation(value="readJsonWithUniqueIdentfierFromFeatureFile")
    public static Sugar9999 getJsonData(String value) {
        JsonUtility jsonUtility = new JsonUtility();
        List<Sugar9999> inputFormList = jsonUtility.jsonReadAll(Sugar9999.class);
        Sugar9999 inputForm = new Sugar9999();
        for (Sugar9999 form : inputFormList) {
            if (form.getFileNumber().equalsIgnoreCase(value)) {
                inputForm.setFileNumber(form.getFileNumber());
                inputForm.setAssignedTo(form.getAssignedTo());
                inputForm.setDateOfOccurrence(form.getDateOfOccurrence());
                inputForm.setStatus(form.getStatus());
                inputForm.setPoo_Code(form.getPoo_Code());
                inputForm.setPoo_Name(form.getPoo_Name());
            }
        }
        return inputForm;
    }
}
