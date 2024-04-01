package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("LogIn.fxml"));
			Scene scene = new Scene(root,371,540);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Timetable and Grades App");
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(event -> {
				event.consume();
				logout(primaryStage);	
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
		
	
public void logout(Stage primaryStage){	
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("You're about to logout!");
		alert.setContentText("Do you want to save before exiting?");
		
		if (alert.showAndWait().get() == ButtonType.OK){
			System.out.println("You successfully logged out");
			primaryStage.close();
		} 
	} 
	
	public static void main(String[] args) {
		launch(args);
	}
}
