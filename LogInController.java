package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connectivity.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LogInController {
	
    @FXML
    private Label output;
	
	@FXML
	private TextField txtLogin;
	
	@FXML
	private PasswordField txtPwd;
	
	@FXML
	void onLoginClicked(ActionEvent event) {
		if (login().equals("successful"))
		{
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
        		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AllHome.fxml"));
    			Stage allhome = (Stage)((Node)event.getSource()).getScene().getWindow();
        		Scene scene = new Scene(root);
    			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    			allhome.setScene(scene);
    			allhome.show();
    			
        	} catch(Exception e) {
    			e.printStackTrace();
    		}

		}
	}
	
	Connection con = ConnectionUtil.conDB();
	PreparedStatement preparedstatement = null;
	ResultSet resultset;

	static String Name;
	String Password;	
	
	private String login() {
		String status = "successful";
		Name = (txtLogin.getText().toString());
		Password = txtPwd.getText().toString();
		
	    if(Name.isEmpty() || Password.isEmpty()) {
	        status = "Error";
	    } else {
	        String sql = "SELECT Name, Password, ClassRep FROM info Where Name = ? and Password = ? ";
	        try {
	            preparedstatement = con.prepareStatement(sql);
				preparedstatement.setString(1, Name);
				preparedstatement.setString(2, Password);
	            
				resultset = preparedstatement.executeQuery();
	            if (!resultset.next()) {
					output.setText("Enter Correct Name/Password");
	                status = "Error";

	            } else 
	            	output.setText("Login Successful..Redirecting..");
	        } catch (SQLException ex) {
	          System.err.println(ex.getMessage());
	           status = "Exception";
	        }
	    }
	    return status;
	}
}