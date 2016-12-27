package controller;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import static controller.SubClinicFxmlDocumentController.showError;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import model.patientModel;
import model.userModel;

/**
 *
 * @author MOHAMMED
 */

public class UsersController implements Initializable{
 
     @FXML
    private JFXTreeTableView<userModel> userTableView;
     
     ObservableList<userModel> userstList;
     
     
    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static String url = "jdbc:mysql://localhost:3306";
    private static String Password = "root";
    private static String username = "root";
    ResultSet result;
     
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JFXTreeTableColumn<userModel,String> username=new JFXTreeTableColumn<>("User Name");
        
        username.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
           return param.getValue().getValue().user;
            }
        });
        
        
         JFXTreeTableColumn<userModel,String> password=new JFXTreeTableColumn<>("Password");
        
        password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<userModel, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<userModel, String> param) {
           return param.getValue().getValue().password;
            }
         });
        
        userstList = FXCollections.observableArrayList();
        addrowsToTable();
        TreeItem<userModel> root = new RecursiveTreeItem<userModel>(userstList, RecursiveTreeObject::getChildren);
        userTableView.getColumns().addAll(username, password);
        userTableView.setRoot(root);
        userTableView.setShowRoot(false);
    
     
    }
    
    
     void addrowsToTable() {

        String sqlSelect = "select * from test.register ";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            while (result.next()) {
                userstList.add(new userModel(result.getString(2), result.getString(3)));

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
}
