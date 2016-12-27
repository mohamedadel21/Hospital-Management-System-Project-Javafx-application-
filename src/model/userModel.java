package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author MOHAMMED
 */

public class userModel extends RecursiveTreeObject<userModel>{
    
   public StringProperty user,password;

    public userModel(String user,String password) {
        
        this.user=new SimpleStringProperty(user);
        this.password=new SimpleStringProperty(password);
        
        
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    public void setUser(StringProperty user) {
        this.user = user;
    }

    public StringProperty getPassword() {
        return password;
    }

    public StringProperty getUser() {
        return user;
    }
    
    

   
    
}
