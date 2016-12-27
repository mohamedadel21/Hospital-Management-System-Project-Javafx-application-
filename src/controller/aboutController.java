package controller;

import Main.Signin;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author MOHAMMED
 */
public class aboutController implements Initializable{
 @FXML
    private ImageView twitter;

    @FXML
    private ImageView in;


    @FXML
    private ImageView fb;

    
 Signin su;
        Stage stage;
        public  void Main( Signin su,Stage stage){
            this.stage=stage;
        this.su=su;
        }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

       fb.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.facebook.com/mohamedadel221"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
       in.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://eg.linkedin.com/in/mohamed-adel-891297a6"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
       twitter.addEventHandler(MouseEvent.MOUSE_CLICKED,e ->{
        try {
        Desktop.getDesktop().browse(new URI("https://www.twitter.com/mohamedadel800"));

    } catch (IOException ex) {
        ex.printStackTrace();
        }   catch (URISyntaxException ex) {
        ex.printStackTrace();
        }
       });
       
    }

    @FXML
    void openTwitterLink(ActionEvent event) {
     try {
 java.awt.Desktop.getDesktop().browse(new URI("https://eg.linkedin.com/in/mohamed-adel-891297a6"));
     } catch (URISyntaxException ex) {
        ex.printStackTrace();   
     } catch (IOException ex) {
        ex.printStackTrace();   
     }
    }
}
