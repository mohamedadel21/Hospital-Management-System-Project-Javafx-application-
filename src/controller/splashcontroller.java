
package controller;

import Main.Signin;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author MOHAMMED
 */
public class splashcontroller implements Initializable {
    @FXML
    private AnchorPane splashAnchorPane;
   
        Signin su;
        Stage stage;
        
      public  void Main( Signin su,Stage stage){
        this.stage=stage;
        this.su=su;
        
        }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       new splash().start();
    }
    
    class splash extends Thread{

        @Override
        public void run() {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(1500);
                        FadeTransition fadeout=new FadeTransition(Duration.seconds(4), splashAnchorPane);
                        fadeout.setFromValue(1);
                        fadeout.setToValue(0);
                        fadeout.setCycleCount(1);
                        fadeout.play();
                        
                        fadeout.setOnFinished(e ->{
                         su.splashWindowClose();
                         su.signinWindow();

                        });
                       
                    } 
                    catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    
    }
    
}
