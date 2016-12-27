package controller;

import Main.Signin;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */
public class ClinicsMainWindowController implements Initializable {

    Signin signin;
    Stage stage;
    public static String tableName = "";

    public void main(Signin signin, Stage stage) {
        this.signin = signin;
        this.stage = stage;
    }

    @FXML
    private Pane backgroundPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger humburger;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {

            FadeTransition fadeout = new FadeTransition(Duration.seconds(2.5), backgroundPane);
            fadeout.setFromValue(1);
            fadeout.setToValue(0);
            fadeout.play();

            fadeout.setOnFinished(event -> {

                backgroundPane.setStyle(" -fx-background-image: url(\"/src/image-1.jpg\");");

                FadeTransition fadein = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                fadein.setFromValue(0);
                fadein.setToValue(0.6);
                fadein.play();

                fadein.setOnFinished(e -> {

                    backgroundPane.setStyle(" -fx-background-image: url(\"/src/sgh-main-banner4.jpg\");");
                    FadeTransition fadein2 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                    fadein2.setFromValue(0);
                    fadein2.setToValue(1);
                    fadein2.play();

                    fadein2.setOnFinished(event2 -> {

                        backgroundPane.setStyle(" -fx-background-image: url(\"/src/banner_aae1.jpg\");");

                        FadeTransition fadein3 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                        fadein3.setFromValue(1);
                        fadein3.setToValue(0);
                        fadein3.play();

                        fadein3.setOnFinished(event3 -> {
                            backgroundPane.setStyle(" -fx-background-image: url(\"/src/image-1.jpg\");");

                            FadeTransition fadein4 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                            fadein4.setFromValue(0);
                            fadein4.setToValue(1);
                            fadein4.play();

                            fadein4.setOnFinished(event4 -> {
                                backgroundPane.setStyle(" -fx-background-image: url(\"/src/banner5.jpg\");");

                                FadeTransition fadein5 = new FadeTransition(Duration.seconds(2.5), backgroundPane);
                                fadein5.setFromValue(0);
                                fadein5.setToValue(1);
                                fadein5.play();

                            });

                        });
                    });

                });

            });

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/navigationDrawerFXML.fxml"));
            AnchorPane pane = loader.load();
            drawer.setSidePane(pane);
            HamburgerBackArrowBasicTransition hamburderTrans = new HamburgerBackArrowBasicTransition(humburger);
            hamburderTrans.setRate(-1);
            humburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                hamburderTrans.setRate(hamburderTrans.getRate() * -1);
                hamburderTrans.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void OpenClinicWindow0(ActionEvent event) {
        try {
            
            tableName = "internalmedicine";
            signin.clinicName = "Internal Medicine";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();
        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow1(ActionEvent event) {
        try {
            tableName = "physiotherapy";
            signin.clinicName = "Physiotherapy";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow2(ActionEvent event) {
        try {
            tableName = "neurology";
            signin.clinicName = "Neurology";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow3(ActionEvent event) {
        try {
            tableName = "cardiology";
            signin.clinicName = "cardiology";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow4(ActionEvent event) {
        try {
            tableName = "earnose";
            signin.clinicName = "Ear and Nose";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow5(ActionEvent event) {
        try {
            tableName = "orthopedics";
            signin.clinicName = "Orthopedics";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow6(ActionEvent event) {
        try {
            tableName = "dentalclinic";
            signin.clinicName = "Dental";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow7(ActionEvent event) {
        try {
            tableName = "dermatology";
            signin.clinicName = "Dermatology";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }

    @FXML
    public void OpenClinicWindow8(ActionEvent event) {
        try {
            tableName = "ophthalmology";
            signin.clinicName = "Ophthalmology";
            signin.SubClinicWindow();
            signin.clinicsWindowClose();

        } catch (Exception ex) {
        }
    }
}
