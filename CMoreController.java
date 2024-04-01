package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class CMoreController extends AllMoreController{
	
    @FXML
    private Button btnMore;

    @FXML
    private AnchorPane scenePane;
    
    @FXML
    void onChoiceCliked(ActionEvent event) {
 		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("SelectChoices.fxml"));
			Stage schoices = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			schoices.setScene(scene);
			schoices.show();
		}catch (IOException e) {
			e.printStackTrace();
		}    
		
    }

}