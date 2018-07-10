package SL.app;
//Mohammad Bilal Memon(MBM186) and Jiya Kohli(JK1316)
import SL.view.SongLibController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SongLib extends Application {
	private static Stage pStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				getClass().getResource("/SL/view/SongLib.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		SongLibController SC = loader.getController();
		SC.start(primaryStage); 
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Song Library Assignment for Software Meth.");
		primaryStage.setResizable(false); 
		primaryStage.show();
	   
	}
	public static void main(String[] args) {
		   launch(args);
	}
	
	
}
