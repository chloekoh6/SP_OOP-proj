package application;

import java.io.IOException;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ChoicesController extends AllHomeController{

    @FXML
    void onBackClicked(ActionEvent event) {
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
    }
}
