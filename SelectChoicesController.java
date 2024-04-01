package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Connectivity.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class SelectChoicesController extends AllHomeController implements Initializable {
    private ObservableList<String> mcinter = FXCollections.observableArrayList("Wednesday, 13:00 - 15:00", "Thursday, 8:00 - 10:00");
    private ObservableList<String> mcinterl = FXCollections.observableArrayList("Monday, 8:00 - 10:00", "Monday, 10:00 - 12:00");
    private ObservableList<String> mdsa = FXCollections.observableArrayList("Thursday, 11:00 - 13:00", "Thursday, 13:00 - 15:00");
    private ObservableList<String> mdsal = FXCollections.observableArrayList("Monday, 8:00 - 10:00", "Monday, 10:00 - 12:00");
    private ObservableList<String> msae = FXCollections.observableArrayList("Tuesday, 13:00 - 14:30", "Wednesday, 9:00 - 10:30", "Wednesday, 13:00 - 15:00", "Thursday, 15:00 - 16:30");
    private ObservableList<String> mdoaiot = FXCollections.observableArrayList("Wednesday, 12:00 - 15:00", "Friday, 14:00 - 17:00");

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Cinter.setItems(mcinter);
    	Cinter.getSelectionModel().select(0); 
    	Cinter1.setItems((ObservableList<String>)mcinterl);
    	Cinter1.getSelectionModel().select(0);
    	Dsa.setItems((ObservableList<String>)mdsa);
    	Dsa.getSelectionModel().select(0);	
    	Dsa1.setItems((ObservableList<String>)mdsal);
    	Dsa1.getSelectionModel().select(0);
    	SAE.setItems((ObservableList<String>)msae);
    	SAE.getSelectionModel().select(0);
    	Doaiot.setItems((ObservableList<String>)mdoaiot);
    	Doaiot.getSelectionModel().select(0);
	}

    @FXML
    private ComboBox<String> Cinter;
    
    @FXML
    private ComboBox<String> Cinter1;

    @FXML
    private ComboBox<String> Doaiot;

    @FXML
    private ComboBox<String> Dsa;
    
    @FXML
    private ComboBox<String> Dsa1;

    @FXML
    private ComboBox<String> SAE;
    
	Connection con = ConnectionUtil.conDB();
	PreparedStatement preparedstatement = null, ps = null;
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

	private void insert(String day, String mod, String start, String end, String lec, String loc) {
		String fclass = findclass();
		if(fclass.equals("01")) {
    		String sql = "INSERT INTO `01timings`( `Day`, `Module`, `StartTime`, `EndTime`, `Lecturer`, `Location`) VALUES (?,?,?,?,?,?)";
	    	try {
	    		preparedstatement = con.prepareStatement(sql);
	    		preparedstatement.setString(1, day);
	    		preparedstatement.setString(2, mod);
	    		preparedstatement.setString(3, start);
	    		preparedstatement.setString(4, end);
	    		preparedstatement.setString(5, lec);
	    		preparedstatement.setString(6, loc);
	    		
	    		boolean rs = preparedstatement.execute();
	    		if(rs)
	    			System.out.println("Insert successful");
	    	} catch (SQLException ex) {
		          System.err.println(ex.getMessage());
	    	}
		}
		else {
			String sql = "INSERT INTO `02timings`( `Day`, `Module`, `StartTime`, `EndTime`, `Lecturer`, `Location`) VALUES (?,?,?,?,?,?)";
	    	try {
	    		preparedstatement = con.prepareStatement(sql);
	    		preparedstatement.setString(1, day);
	    		preparedstatement.setString(2, mod);
	    		preparedstatement.setString(3, start);
	    		preparedstatement.setString(4, end);
	    		preparedstatement.setString(5, lec);
	    		preparedstatement.setString(6, loc);
	    		
	    		boolean rs = preparedstatement.execute();
	    		if(rs)
	    			System.out.println("Insert successful");
	    	} catch (SQLException ex) {
		          System.err.println(ex.getMessage());
	    	}
		}
    }

    @FXML
    void onSubmitClicked(ActionEvent event) {
    	switch(Cinter.getSelectionModel().getSelectedIndex()) {
	    	case 0: {
	    		insert("3.Wednesday", "Cinter", "13:00", "15:00", "Mr. ", "T6"); 
	    		Cinter.getItems().remove(0);
	    		break;
	    	}
	    	case 1:{
	    		insert("3.Wednesday", "Cinter", "13:00", "15:00", "Mr. ", "T6"); 
	    		Cinter.getItems().remove(1);
	    		break;
	    	}
	    }
    	switch(Cinter1.getSelectionModel().getSelectedIndex()) {
	    	case 0: {
	    		insert("1.Monday", "Cinter Lab", "8:00", "10:00", "Mr. ", "T9"); 
	    		Cinter1.getItems().remove(0);
	    		break;
	    	}
	    	case 1:{
	    		insert("1.Monday", "Cinter Lab", "10:00", "12:00", "Mr. ", "T9"); 
	    		Cinter1.getItems().remove(1);
	    		break;
	    	}	
	    }
    	switch(Dsa.getSelectionModel().getSelectedIndex()) {
	    	case 0: {
	    		insert("4.Thursday", "DSA", "11:00", "13:00", "Mrs. ", "T6"); 
	    		Dsa.getItems().remove(0);
	    		break;
	    	}
	    	case 1:{
	    		insert("4.Thursday", "DSA", "13:00", "15:00", "Mrs. ", "T6"); 
	    		Dsa.getItems().remove(1);
	    		break;
	    	}
	    }
		switch(Dsa1.getSelectionModel().getSelectedIndex()) {
	    	case 0: {
	    		insert("1.Monday", "DSA Lab", "8:00", "10:00", "Mrs. ", "T9"); 
	    		Dsa1.getItems().remove(0);
	    		break;
	    	}
	    	case 1:{
	    		insert("1.Monday", "DSA Lab", "10:00", "12:00", "Mrs. ", "T9"); 
	    		Dsa1.getItems().remove(1);
	    		break;
	    	}	
	    }
		switch(SAE.getSelectionModel().getSelectedIndex()) {
	    	case 0: {
	    		insert("2.Tuesday", "SAE", "13:00", "14:30", "Mrs. ", "T6"); 
	    		SAE.getItems().remove(0);
	    		break;
	    	}
	    	case 1:{
	    		insert("3.Wednesday", "SAE", "9:00", "10:30", "Mrs. ", "T6"); 
	    		SAE.getItems().remove(1);
	    		break;
	    	}
	    	case 2: {
	    		insert("3.Wednesday", "SAE", "13:00", "14:30", "Mrs. ", "T6"); 
	    		SAE.getItems().remove(2);
	    		break;
	    	}
	    	case 3:{
	    		insert("4.Thursday", "SAE", "15:00", "16:30", "Mrs. ", "T6"); 
	    		SAE.getItems().remove(3);
	    		break;
	    	}
		}
		switch(Doaiot.getSelectionModel().getSelectedIndex()) {
	    	case 0: {
	    		insert("3.Wednesday", "DOAIOT", "12:00", "15:00", "Mr ", "T10"); 
	    		Doaiot.getItems().remove(0);
	    		break;
	    	}
	    	case 1:{
	    		insert("5.Friday", "DOAIOT", "12:00", "17:00", "Mr ", "T10"); 
	    		Doaiot.getItems().remove(1);
	    		break;
	    	}	
	    }
    }    
}
