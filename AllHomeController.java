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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AllHomeController {
	
	Connection con = ConnectionUtil.conDB();
	PreparedStatement preparedstatement = null;
	String name = LogInController.Name;

    @FXML
    void onMoreClicked(ActionEvent event) {
        try {
        	String sql = "SELECT Name, ClassRep FROM info WHERE Name = '"+name+"'";
			preparedstatement = con.prepareStatement(sql);
	        ResultSet rs = preparedstatement.executeQuery();

	        while(rs.next()) {
	        	String role = rs.getString("ClassRep");
		         if (role.equals("1")) {
		     		AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CMore.fxml"));
					Stage cmore = (Stage)((Node)event.getSource()).getScene().getWindow();
		    		Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					cmore.setScene(scene);
					cmore.show();
		         }
		         else {
		        	AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("AllMore.fxml"));
					Stage allmore = (Stage)((Node)event.getSource()).getScene().getWindow();
		    		Scene scene = new Scene(root);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					allmore.setScene(scene);
					allmore.show();
		         }
	        }
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

}

