/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Main.Signin;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */
public class NavigationDrawerFXMLController implements Initializable {

    @FXML
    private Label NameLB;

    @FXML
    private Label usernameLB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usernameLB.setText(SigninFXMLController.usernameforHome);
        NameLB.setText(SigninFXMLController.nameforHome);
    }

    @FXML
    void showStatistics(ActionEvent event) {
        Signin.StatisticsWindow();
    }

    @FXML
    void about(ActionEvent event) {
        Signin.AboutWindow();
    }

    @FXML
    void exit(ActionEvent event) {

        Signin.clinicsWindowClose();
    }
}
