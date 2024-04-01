package application;

import java.io.IOException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AllMoreController {	
	
	@FXML
    private Button btnMore;
	
    @FXML
    private Button logout;
	
	@FXML
    private AnchorPane scenePane;
	
	Connection con = ConnectionUtil.conDB();
	PreparedStatement ps = null;
	String name = LogInController.Name;
	
	public String findclass() {
		String fclass = "0";
		String find = "SELECT Name, Class FROM info WHERE Name = '"+name+"'";
    	try {
        	ps = con.prepareStatement(find);
   	    	ResultSet resultset = ps.executeQuery();
   	    	if(resultset.next()) {
   	    		fclass = resultset.getString("Class");
	   	    	if (fclass.equals("DCPE/FT/2A01")) 
	   	    		fclass = "01";
	   	    	else
	   	    		fclass = "02";
   	    	}
   		}catch (SQLException ex) {
   			System.err.println(ex.getMessage());
   		}    	
    	return fclass;
	}

    @FXML
    void onClassCliked(ActionEvent event) {
    	String fclass = findclass();
    	if (fclass.equals("01")) {
    		AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("Timetable.fxml"));
				Stage timetable = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				timetable.setScene(scene);
				timetable.show();
			}catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		AnchorPane root;
			try {
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("Timetable1.fxml"));
				Stage timetable1 = (Stage)((Node)event.getSource()).getScene().getWindow();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				timetable1.setScene(scene);
				timetable1.show();
			}catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void onGradesCliked(ActionEvent event) {
 		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("GradesOOP.fxml"));
			Stage grades = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			grades.setScene(scene);
			grades.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void onFriendsCliked(ActionEvent event) {
    	AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("FriendsTable.fxml"));
			Stage ft = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			ft.setScene(scene);
			ft.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void onLogOutCliked(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?: ");
		
		if(alert.showAndWait().get() == ButtonType.OK){
			Stage Window4 = (Stage) scenePane.getScene().getWindow();
			System.out.println("You successfully logged out!");
			Window4.close();
		}
    }

    @FXML
    void onMoreClicked(ActionEvent event) {
 		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("AllHome.fxml"));
			Stage allhome = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			allhome.setScene(scene);
			allhome.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
    }

}
