package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Timetable1Controller extends AllHomeController implements Initializable {
    
	ObservableList<TbData> data = FXCollections.observableArrayList();
	
	public void initialise() {
		try {
			TbData tbdata;
	    	String sql = "SELECT * FROM `02timings`";
	        preparedstatement = con.prepareStatement(sql);
	        ResultSet rs = preparedstatement.executeQuery();
								
	        while(rs.next()) {
				String day = rs.getString("Day");
				String module = rs.getString("Module");
				String start = rs.getString("StartTime");
				String end = rs.getString("EndTime"); 	
				String lecturer = rs.getString("Lecturer");
				String location = rs.getString("Location");
				tbdata = new TbData(day, module, start, end, lecturer, location);
				data.add(tbdata);
			}
	    }catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    }
	} 

    @FXML
    private TableView<TbData> tbData;
    
    @FXML
    private TableColumn<TbData, String> days;

    @FXML
    private TableColumn<TbData, String> endt;

    @FXML
    private TableColumn<TbData, String> loca;

    @FXML
    private TableColumn<TbData, String> startt;

    @FXML
    private TableColumn<TbData, String> lect;

    @FXML
    private TableColumn<TbData, String> modu;

    
    @FXML
    void onGoClicked(ActionEvent event) {
 		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("Choices.fxml"));
			Stage choices = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			choices.setScene(scene);
			choices.show();
		}catch (IOException e) {
			e.printStackTrace();
		}    
    }
    
@SuppressWarnings("unchecked")
@Override
    public void initialize(URL url, ResourceBundle rb) {
	   	days.setCellValueFactory(new PropertyValueFactory<>("day"));
		modu.setCellValueFactory(new PropertyValueFactory<>("mod"));
		startt.setCellValueFactory(new PropertyValueFactory<>("start"));
		endt.setCellValueFactory(new PropertyValueFactory<>("end"));
		lect.setCellValueFactory(new PropertyValueFactory<>("lec"));		
		loca.setCellValueFactory(new PropertyValueFactory<>("loc"));
		initialise();
		tbData.setItems(data);
		
	 tbData.getSortOrder().addAll(days, startt);
	 tbData.sort();
  }    

}
