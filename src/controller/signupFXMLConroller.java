package controller;

import Main.Signin;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import static controller.SubClinicFxmlDocumentController.showError;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 *
 * @author MOHAMMED
 */

public class signupFXMLConroller implements Initializable {

    @FXML
    private JFXTextField firstName;

    @FXML
    private JFXTextField lastName;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField userName;

    Stage stage;
    static Signin s;

    public void main(Signin s) {
        this.s = s;

    }

    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static String url = "jdbc:mysql://localhost:3306";
    private static String Password = "root";
    private static String username = "root";
    private static String sqlInsert;

    public static void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error ");
        alert.setHeaderText("there is an error happened !");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign up ");
        alert.setHeaderText("Information");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
     public  RequiredFieldValidator validator(String msg){
        RequiredFieldValidator validator=new RequiredFieldValidator();
        validator.setOpacity(0.5);
        validator.setMessage(msg);         
        return validator;
        }

    private static void insert(String name, String user_name, String password) {

        sqlInsert = "INSERT INTO test.register(name,username,password) VALUES (?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlInsert);

            stat.setString(1, name);
            stat.setString(2, user_name);
            stat.setString(3, password);

            stat.executeUpdate();
            showInfo(user_name + " has registered successfully");
            s.signUPClose();
            s.signinWindow();

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
    void regist(ActionEvent event) {
        try {
            if (firstName.getText().isEmpty() || lastName.getText().isEmpty()
                    | userName.getText().isEmpty() || password.getText().isEmpty()) {

                firstName.validate();
                lastName.validate();
                userName.validate();
                password.validate();

                
            } else {

                insert(firstName.getText().toString() + " " + lastName.getText().toString(), userName.getText().toString(), password.getText().toString());

            }
        } catch (NumberFormatException c) {
            showError(c.getMessage());

        } catch (NullPointerException cc) {
            showError("Please , Fill all fields");

        } catch (Error e) {
            showError(e.getMessage());
        } catch (Exception f) {
            showError(f.getMessage());

        }
    }

    @FXML
    void cancelAction(ActionEvent event) {
        s.signUPClose();
        s.signinWindow();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        firstName.getValidators().add(validator("Input is required"));
        lastName.getValidators().add(validator("Input is required"));
        userName.getValidators().add(validator("Input is required"));
        password.getValidators().add(validator("Input is required"));

    }

}
