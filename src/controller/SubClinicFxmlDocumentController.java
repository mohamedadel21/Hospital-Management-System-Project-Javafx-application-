package controller;

import Main.Signin;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.validation.RequiredFieldValidator;
import static controller.signupFXMLConroller.showError;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.patientModel;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */

public class SubClinicFxmlDocumentController implements Initializable {

    @FXML
    private JFXTreeTableView<patientModel> tableView;
    ObservableList<patientModel> patientList;
    JFXDatePicker datePicker;
    @FXML
    private JFXTextField searchTF;

    @FXML
    private JFXTextField doctorNameTF, patientNameTF, ageTF;
    @FXML
    private JFXTextArea treatmentTF, diagnosisTF;

    @FXML
    private GridPane InsertGridPane;

    @FXML
    private Label patientageLebel, patientNameLebel, treatmentLabel, dateLabel, diagnosisLebel, DoctorNameLebel;

    String Pname, Dname, Page, Pdate, Pdiagnosis, Ptreatment;

    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static String url = "jdbc:mysql://localhost:3306";
    private static String Password = "root";
    private static String username = "root";
    private static String sqlInsert;
    ResultSet result;

    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    Signin signin;
    Stage stage;

    public void main(Signin signin, Stage stage) {
        this.signin = signin;
        this.stage = stage;
    }

    public  RequiredFieldValidator validator(String msg){
        RequiredFieldValidator validator=new RequiredFieldValidator();
        validator.setOpacity(0.5);
        validator.setMessage(msg);         
        return validator;
        }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        patientNameTF.getValidators().add(validator("Input is required"));
        doctorNameTF.getValidators().add(validator("Input is required"));
        ageTF.getValidators().add(validator("Age must be numeric"));
        diagnosisTF.getValidators().add(validator("Input is required"));
        treatmentTF.getValidators().add(validator("Input is required"));

        datePicker = new JFXDatePicker();
        datePicker.setPrefWidth(240);
        datePicker.setPrefHeight(41);

        InsertGridPane.add(datePicker, 1, 3);

        JFXTreeTableColumn<patientModel, String> PNcolumn = new JFXTreeTableColumn<>("Pateint Name");

        PNcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().patientName;

            }
        });

        JFXTreeTableColumn<patientModel, String> DNcolumn = new JFXTreeTableColumn<>("Doctor Name");

        DNcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().doctorName;

            }
        });

        JFXTreeTableColumn<patientModel, String> Agecolumn = new JFXTreeTableColumn<>("Age");

        Agecolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().Age;

            }
        });

        JFXTreeTableColumn<patientModel, String> Datecolumn = new JFXTreeTableColumn<>("Date");

        Datecolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().Date;

            }
        });

        JFXTreeTableColumn<patientModel, String> Diagnosiscolumn = new JFXTreeTableColumn<>("Diagnosis");

        Diagnosiscolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().diagnosis;

            }
        });

        JFXTreeTableColumn<patientModel, String> Teatmentcolumn = new JFXTreeTableColumn<>("Treatment");
        Teatmentcolumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<patientModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<patientModel, String> param) {
                return param.getValue().getValue().treatment;

            }
        });

        patientList = FXCollections.observableArrayList();
        addrowsToTable();

        TreeItem<patientModel> root = new RecursiveTreeItem<patientModel>(patientList, RecursiveTreeObject::getChildren);
        tableView.getColumns().addAll(PNcolumn, DNcolumn, Agecolumn, Datecolumn, Diagnosiscolumn, Teatmentcolumn);
        tableView.setRoot(root);
        tableView.setShowRoot(false);

        searchTF.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                tableView.setPredicate(new Predicate<TreeItem<patientModel>>() {

                    @Override
                    public boolean test(TreeItem<patientModel> t) {

                        boolean flag = t.getValue().patientName.getValue().contains(newValue)
                                || t.getValue().doctorName.getValue().contains(newValue)
                                || t.getValue().Age.getValue().contains(newValue)
                                || t.getValue().Date.getValue().contains(newValue)
                                || t.getValue().diagnosis.getValue().contains(newValue)
                                || t.getValue().treatment.getValue().contains(newValue);
                        return flag;

                    }
                });
            }

        });

        tableView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue)
                -> 
                showDetails(newValue)
        );
    }

    public void showDetails(TreeItem<patientModel> pModel) {
        patientNameLebel.setText(pModel.getValue().getPatientName());
        DoctorNameLebel.setText(pModel.getValue().getDoctorName());
        patientageLebel.setText(pModel.getValue().getAge());
        dateLabel.setText(pModel.getValue().getDate());
        diagnosisLebel.setText(pModel.getValue().getDiagnosis());
        treatmentLabel.setText(pModel.getValue().getTreatment());

        patientNameTF.setText(pModel.getValue().getPatientName());
        doctorNameTF.setText(pModel.getValue().getDoctorName());
        ageTF.setText(pModel.getValue().getAge());
        datePicker.setValue(LocalDate.parse(pModel.getValue().getDate()));
        diagnosisTF.setText(pModel.getValue().getDiagnosis());
        treatmentTF.setText(pModel.getValue().getTreatment());

        Pname = pModel.getValue().getPatientName();
        Dname = pModel.getValue().getDoctorName();
        Page = pModel.getValue().getAge();
        Pdate = pModel.getValue().getDate();
        Pdiagnosis = pModel.getValue().getDiagnosis();
        Ptreatment = pModel.getValue().getTreatment();

    }

    private static void insert(String patientname, String doctorname, int age, String date, String diagnosis, String treatment) {
        try {

            sqlInsert = "INSERT INTO test." + ClinicsMainWindowController.tableName + "(patientname,doctorname,age,date,diagnosis,treatment) VALUES (?,?,?,?,?,?)";

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlInsert);

            stat.setString(1, patientname);
            stat.setString(2, doctorname);
            stat.setInt(3, age);
            stat.setString(4, date);
            stat.setString(5, diagnosis);
            stat.setString(6, treatment);

            stat.executeUpdate();

        } catch (SQLException ex) {
            showError(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            showError(ex.getMessage());

        } catch (NumberFormatException c) {
            showError(c.getMessage());

        } catch (NullPointerException cc) {
            showError(cc.getMessage());

        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());

        } finally {
            try {
                conn.close();
                stat.close();
            } catch (SQLException ex) {
                showError(ex.getMessage());
            }
        }
    }

    @FXML
    void insertPatientData(ActionEvent event) {
        try {
           
            insert(patientNameTF.getText(), doctorNameTF.getText(), Integer.parseInt(ageTF.getText()), datePicker.getValue().toString(), diagnosisTF.getText(), treatmentTF.getText());
            patientList.add(new patientModel(patientNameTF.getText(),
            doctorNameTF.getText(), ageTF.getText(), datePicker.getValue().toString(), diagnosisTF.getText(), treatmentTF.getText()));
       
        }
        catch (NullPointerException cc) {
            showError("Please , All inputs are requires");

        } 
        catch (NumberFormatException c) {
            showError("Age must be number");
            
        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());

        }
    }

    void addrowsToTable() {

        String sqlSelect = "select * from test." + ClinicsMainWindowController.tableName + " ";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            while (result.next()) {
                patientList.add(new patientModel(result.getString(1), result.getString(2), result.getInt(3) + "", result.getString(4), result.getString(5), result.getString(6)));

            }
        } catch (SQLException r) {
            showError(r.getMessage());
        } catch (ClassNotFoundException n) {
            showError(n.getMessage());
        } catch (NullPointerException l) {
            showError(l.getMessage());
        } finally {
            try {
                conn.close();
                stat.close();
            } catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }

    }

    @FXML
    void delelePatientRow(ActionEvent event) {
        try {
            int index = tableView.getSelectionModel().getSelectedIndex();
            patientList.remove(index);
            String sqlSelect = "delete  from test." + ClinicsMainWindowController.tableName + " where patientname='" + tableView.getSelectionModel().getSelectedCells().get(index).getTreeItem().getValue().getPatientName() + "'";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);
            stat.executeUpdate();
            clear();
        } catch (SQLException r) {
            showError(r.getMessage());
        } catch (ClassNotFoundException n) {
            showError(n.getMessage());
        } catch (NullPointerException l) {
            showError(l.getMessage());
        } finally {
            try {
                conn.close();
                stat.close();
            } catch (SQLException rr) {
                showError(rr.getMessage());
            }
        }

    }

    @FXML
    void updatePatientRow(ActionEvent event) {

        int index = tableView.getSelectionModel().getSelectedIndex();
        TreeItem<patientModel> pModel = tableView.getSelectionModel().getSelectedItem();

        patientModel PatientModel = new patientModel(patientNameTF.getText(), doctorNameTF.getText(), ageTF.getText(), datePicker.getValue().toString(), diagnosisTF.getText(), treatmentTF.getText());
        pModel.setValue(PatientModel);

        String sqlUpdat = "UPDATE  test." + ClinicsMainWindowController.tableName + " SET patientname='" + patientNameTF.getText() + "' ,doctorname='" + doctorNameTF.getText() + "' , "
                + "  age='" + ageTF.getText() + "',date='" + datePicker.getValue().toString() + "',diagnosis='" + diagnosisTF.getText() + "', treatment='" + treatmentTF.getText() + "' "
                + " WHERE patientname='" + Pname + "' and" + " doctorname='" + Dname + "' and"
                + " age='" + Integer.parseInt(Page) + "' and"
                + " date='" + Pdate + "' and"
                + " diagnosis='" + Pdiagnosis + "' and"
                + " treatment='" + Ptreatment + "'";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlUpdat);
            stat.executeUpdate();

        } catch (SQLException e) {
            showError(e.getMessage());
        } catch (ClassNotFoundException n) {

            showError(n.getMessage());

        } catch (NumberFormatException f) {
            showError(f.getMessage());

        } catch (NullPointerException l) {
            showError(l.getMessage());

        } finally {
            try {
                conn.close();
                stat.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    void clearFields(ActionEvent event) {
        clear();

    }

    public void clear() {

        patientNameTF.setText(null);
        ageTF.setText(null);
        doctorNameTF.setText(null);
        datePicker.setValue(null);
        diagnosisTF.setText(null);
        treatmentTF.setText(null);
        patientNameLebel.setText(null);
        DoctorNameLebel.setText(null);
        patientageLebel.setText(null);
        dateLabel.setText(null);
        diagnosisLebel.setText(null);
        treatmentLabel.setText(null);
    }

    @FXML
    void deleteAll(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confimation");

        alert.setHeaderText("Confirmation");
        alert.setContentText("Make sure that You Will delete all patient data ... ");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                patientList.removeAll(patientList);
                String sqlSelect = "delete  from test." + ClinicsMainWindowController.tableName;
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, username, Password);
                stat = conn.prepareStatement(sqlSelect);
                stat.executeUpdate();
                clear();
            } catch (SQLException r) {
                showError(r.getMessage());
            } catch (ClassNotFoundException n) {
                showError(n.getMessage());
            } catch (NullPointerException l) {
                showError(l.getMessage());
            } finally {
                try {
                    conn.close();
                    stat.close();
                } catch (SQLException rr) {
                    showError(rr.getMessage());
                }
            }
        } else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }

    }

    @FXML
    void back(ActionEvent event) {
        signin.clinicsWindow();
        signin.SubClinicWindowClose();
    }

}
