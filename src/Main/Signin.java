package Main;

import controller.ClinicsMainWindowController;
import controller.SigninFXMLController;
import controller.StatisticsFxmlDocumentController;
import controller.SubClinicFxmlDocumentController;
import controller.UsersController;
import controller.aboutController;
import controller.signupFXMLConroller;
import controller.splashcontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author MOHAMMED
 */
public class Signin extends Application {

    static Stage stageprim, stage1, stage2, stage4, stage3, stage5, stage6,stage7;
    public String clinicName = "";

    @Override
    public void start(Stage stage) throws Exception {
        this.stage1 = stage;
        splashWindow();
    }

    public void splashWindow() {

        try {

            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/splashfxml.fxml"));
            AnchorPane pane = loader.load();
            splashcontroller controller = loader.getController();
            controller.Main(this, stage1);
            Scene scene = new Scene(pane);
            stage1.initStyle(StageStyle.UNDECORATED);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage1.setScene(scene);
            stage1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void splashWindowClose() {
        stage1.close();
    }

    public void signinWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/FXMLSignInDocument.fxml"));
            AnchorPane pane = loader.load();
            SigninFXMLController controller = loader.getController();
            stageprim = new Stage();
            controller.Main(this, stageprim);
            Scene scene = new Scene(pane);
            stageprim.setTitle("Sign in");
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stageprim.setResizable(false);
            stageprim.setScene(scene);
            stageprim.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signInClose() {
        stageprim.close();
    }

    public void signupWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/signUpfxmlDocument.fxml"));
            AnchorPane pane = loader.load();
            signupFXMLConroller controller = loader.getController();
            controller.main(this);
            stage2 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage2.setTitle("Register");
            stage2.setResizable(false);
            stage2.setScene(scene);
            stage2.initStyle(StageStyle.UNDECORATED);
            stage2.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signUPClose() {
        stage2.close();
    }

    public void clinicsWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/FXMLhomeDocument.fxml"));
            AnchorPane pane = loader.load();
            ClinicsMainWindowController controller = loader.getController();
            stage3 = new Stage();
            controller.main(this, stage3);
            Scene scene = new Scene(pane);
            stage3.setTitle("Home");
            stage3.setResizable(true);
            stage3.setScene(scene);
            stage3.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clinicsWindowClose() {
        stage3.close();
    }

    public void SubClinicWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/dentalFxmlDocument.fxml"));
            AnchorPane pane = loader.load();
            SubClinicFxmlDocumentController controller = loader.getController();
            stage4 = new Stage();
            controller.main(this, stage4);
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage4.setTitle(clinicName + " Clinic");
            stage4.setFullScreen(true);
            stage4.setResizable(true);
            stage4.setScene(scene);

            stage4.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SubClinicWindowClose() {
        stage4.close();
    }

    public static void StatisticsWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/statisticsFxmlDocument.fxml"));
            AnchorPane pane = loader.load();
            StatisticsFxmlDocumentController controller = loader.getController();
            stage5 = new Stage();
            // controller.Main(this,stage5);      
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage5.setTitle("Statistics");
            stage5.setResizable(true);
            stage5.setScene(scene);
            stage5.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AboutWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/aboutFxmlDocument.fxml"));
            AnchorPane pane = loader.load();
            aboutController controller = loader.getController();
            stage6 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage6.setTitle("About");
            stage6.setResizable(false);
            stage6.setScene(scene);
            stage6.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     public static void usersWindow() {

        try {
            FXMLLoader loader = new FXMLLoader(Signin.class.getResource("/view/FXMLUsersDocument.fxml"));
            AnchorPane pane = loader.load();
            UsersController controller = loader.getController();
            stage7 = new Stage();
            Scene scene = new Scene(pane);
            scene.getStylesheets().add(Signin.class.getResource("/style/StyleSheet.css").toExternalForm());
            stage7.setTitle("Users");
            stage7.setResizable(false);
            stage7.initOwner(stageprim);
            stage7.initModality(Modality.WINDOW_MODAL);
            stage7.setScene(scene);
            stage7.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
