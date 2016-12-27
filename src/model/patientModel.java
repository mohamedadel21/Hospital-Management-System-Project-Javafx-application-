package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MOHAMMED
 */

public class patientModel extends RecursiveTreeObject<patientModel>{
    
   public StringProperty patientName,Age,Date,doctorName,diagnosis,treatment;

    public patientModel(String patientName,String doctorName,String Age,String Date,String diagnosis,String treatment) {
        
        this.patientName=new SimpleStringProperty(patientName);
        this.Age=new SimpleStringProperty(Age);
        this.Date=new SimpleStringProperty(Date);
        this.diagnosis=new SimpleStringProperty(diagnosis);
        this.treatment=new SimpleStringProperty(treatment);
        this.doctorName=new SimpleStringProperty(doctorName);
        
    }

    public void setAge(String Age) {
        this.Age=new SimpleStringProperty(Age);
    }

    public void setDate(String Date) {
        this.Date=new SimpleStringProperty(Date);
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis=new SimpleStringProperty(diagnosis);
    }

    public void setDoctorName(String doctorName) {
        this.doctorName=new SimpleStringProperty(doctorName);
    }

    public void setPatientName(String patientName) {
        this.patientName=new SimpleStringProperty(patientName);
    }

    public void setTreatment(String treatment) {
        this.treatment=new SimpleStringProperty(treatment);
    }

    public String getAge() {
        return Age.get();
    }

    public String getDate() {
        return Date.get();
    }

    public String getDiagnosis() {
        return diagnosis.get();
    }

    public String getDoctorName() {
        return doctorName.get();
    }

    public String getPatientName() {
        return patientName.get();
    }

    public String getTreatment() {
        return treatment.get();
    }
    
   
    
}
