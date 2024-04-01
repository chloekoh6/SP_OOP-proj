package application;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FriendTableController extends AllHomeController{
	
	public void initialise() {
		try {
			TbData tbdata;
	    	String sql = "SELECT * FROM `01timings`";
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

	public void initialise1() {
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
	
	ObservableList<TbData> data = FXCollections.observableArrayList();

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
    private TextField txtFriend;

    @SuppressWarnings("unchecked")
	@FXML
    void onViewClicked(ActionEvent event) {
    	String Name, fclass;
    	Name = (txtFriend.getText().toString());
    	
		try {
			String sql = "SELECT Name, Class FROM info Where Name = ? ";
			preparedstatement = con.prepareStatement(sql);
			preparedstatement.setString(1, Name);
			
			ResultSet rs = preparedstatement.executeQuery();
			if (rs.next()) {
				fclass = rs.getString("Class");

				days.setCellValueFactory(new PropertyValueFactory<>("day"));
				modu.setCellValueFactory(new PropertyValueFactory<>("mod"));
				startt.setCellValueFactory(new PropertyValueFactory<>("start"));
				endt.setCellValueFactory(new PropertyValueFactory<>("end"));
				lect.setCellValueFactory(new PropertyValueFactory<>("lec"));		
				loca.setCellValueFactory(new PropertyValueFactory<>("loc"));
				if (fclass.equals("DCPE/FT/2A01")) {
					initialise();  
				}
				else {
					initialise1();  
				}
				tbData.setItems(data);
						
				tbData.getSortOrder().addAll(days, startt);
				tbData.sort();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
