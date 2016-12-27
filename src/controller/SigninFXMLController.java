package controller;

import Main.Signin;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import static controller.signupFXMLConroller.showError;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.Icon;
import org.controlsfx.control.Notifications;
/**
 *
 * @author MOHAMMED
 */

public class SigninFXMLController implements Initializable {
    @FXML
    private ImageView setting;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private ImageView logoImage;
    
    @FXML
    private JFXButton signinBTN;

    @FXML
    private JFXButton signupBTN;

    @FXML
    private JFXTextField usernameTF;

    @FXML
    private JFXButton forgotPasswordBRN;

    @FXML
    private JFXPasswordField passwordTF;

        private static Connection conn=null;
        private static PreparedStatement stat=null;
        private static String url="jdbc:mysql://localhost:3306";
        private static String Password="root" ;
        private static String username="root";
        private static String sqlInsert;
        ResultSet result;
    
        public static String usernameforHome="";
        public static String nameforHome="";

        Signin su;
        Stage stage;
        public  void Main( Signin su,Stage stage){
        this.stage=stage;
        this.su=su;
        
        }
    
        public  RequiredFieldValidator validator(String msg){
        RequiredFieldValidator validator=new RequiredFieldValidator();
        validator.setOpacity(0.5);
        validator.setMessage(msg);         
        return validator;
        }
        
    @FXML
    void signinAction(ActionEvent event) {
        
        String sqlSelect="select * from test.register where username='"+usernameTF.getText().toString()+"' and password='"+passwordTF.getText().toString()+"' ";
        
        try {
           Thread.sleep(1000);
         //  Image image=new Image("/src/correctsign.png");
           Class.forName("com.mysql.jdbc.Driver");
           conn=DriverManager.getConnection(url,username,Password);
           stat=conn.prepareStatement(sqlSelect);
       
        result=stat.executeQuery();
      
            if (usernameTF.getText().isEmpty() || passwordTF.getText().isEmpty()) {

                usernameTF.validate();
                passwordTF.validate();
            }
           
            else{
        
       if (result.next()){ 
           if ((result.getString("username")).equals(usernameTF.getText().toString()) 
                  && (result.getString("password")).equals(passwordTF.getText().toString())   ) {
                nameforHome=result.getString("name");
                   usernameforHome=result.getString("username");
                            su.clinicsWindow();
                            su.signInClose();
                            
                            Notifications  notification=Notifications.create()
                                   // .graphic(new ImageView(image))
                                    .title("Sign in complete ")
                                    .text(result.getString("username") +" has loged in")
                                    .hideAfter(Duration.seconds(3))
                                    .position(Pos.BOTTOM_RIGHT);                           
                            notification.showInformation();
           }               
       }
      else if (!result.next()) {               
               showError("username or password is invalid ");                            
                }
        
                
                
        }         
       
       } catch (SQLException r) {
           showError(r.getMessage());
       } catch (ClassNotFoundException n) {
           showError(n.getMessage());
        }    
       catch(NullPointerException l){
           showError(l.getMessage());
       } catch (InterruptedException ex) {
           showError(ex.getMessage());
        }
       finally{
        try {
            conn.close();
           stat.close();
        } 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        catch (SQLException rr) {
           showError(rr.getMessage());
        } 
        }
                  
          
          }
    
      
    
    @FXML
    void signupAction(ActionEvent event) {
        try {
          su.signInClose();
          su.signupWindow();
        } catch (Exception ex) {
        }
    }
   
     
    public void initialize(URL url, ResourceBundle rb) {
        
        usernameTF.getValidators().add(validator("Input is requird"));
        passwordTF.getValidators().add(validator("Input is requird"));

        setting.setOnMouseEntered(e ->{
        RotateTransition rotateTransition=new RotateTransition(Duration.seconds(2), setting);
        rotateTransition.setFromAngle(0);
        rotateTransition.setToAngle(360);
        rotateTransition.play();
        });
        
        setting.setOnMouseClicked(e ->{
        
        Signin.usersWindow();
        });
        
       RotateTransition rotateTransition=new RotateTransition(Duration.seconds(122), logoImage);
       rotateTransition.setFromAngle(0);
       rotateTransition.setToAngle(10*720);
       rotateTransition.play();

    }    

   
    
}
