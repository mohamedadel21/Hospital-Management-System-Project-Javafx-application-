package controller;

import Main.Signin;
import static controller.signupFXMLConroller.showError;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MOHAMMED
 */
public class StatisticsFxmlDocumentController implements Initializable {

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    Signin su;
    Stage stage;

    public void Main(Signin su, Stage stage) {

        this.su = su;
    }

    private static Connection conn = null;
    private static PreparedStatement stat = null;
    private static String Url = "jdbc:mysql://localhost:3306";
    private static String Password = "root";
    private static String username = "root";
    private static String sqlInsert;
    ResultSet result;

    int countPatient;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        XYChart.Series set = new XYChart.Series<>();
        
        countPient("internalmedicine");
        set.getData().add(new XYChart.Data("Internal Medicine", countPatient));
        
        XYChart.Series set2 = new XYChart.Series<>();

        countPient("physiotherapy");
        set2.getData().add(new XYChart.Data("Physiotherapy", countPatient));

        XYChart.Series set3 = new XYChart.Series<>();
        countPient("neurology");
        set3.getData().add(new XYChart.Data("Neurology ", countPatient));
        
        XYChart.Series set4 = new XYChart.Series<>();
        countPient("cardiology");
        set4.getData().add(new XYChart.Data("Cardiology Surgery", countPatient));
        
        XYChart.Series set5 = new XYChart.Series<>();
        countPient("earnose");
        set5.getData().add(new XYChart.Data("Ear, Nose and Throat", countPatient));
        
        XYChart.Series set6 = new XYChart.Series<>();
        countPient("orthopedics");
        set6.getData().add(new XYChart.Data("orthopedics", countPatient));
        
        XYChart.Series set7 = new XYChart.Series<>();
        countPient("dentalclinic");
        set7.getData().add(new XYChart.Data("Dental ", countPatient));
        
        XYChart.Series set8 = new XYChart.Series<>();
        countPient("dermatology");
        set8.getData().add(new XYChart.Data("Dermatology", countPatient));
        
        XYChart.Series set9 = new XYChart.Series<>();
        countPient("ophthalmology");
        set9.getData().add(new XYChart.Data("Ophthalmology", countPatient));

        barChart.getData().addAll(set,set2,set3,set4,set5,set6,set7,set8,set9);

    }

    public void countPient(String tableName) {
        countPatient = 0;
        String sqlSelect = "select * from test." + tableName;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Url, username, Password);
            stat = conn.prepareStatement(sqlSelect);

            result = stat.executeQuery();

            while (result.next()) {
                countPatient++;
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
