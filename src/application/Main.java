package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import muiscdatabse.Operationclass;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader root=new FXMLLoader();
			root.setLocation(getClass().getResource("sample.fxml")); 

			
			Scene scene = new Scene(root.load(),700,600);
			
			primaryStage.setScene(scene);
	primaryStage.setTitle("Songs playlist");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		Operationclass.getinstanceofOperationclass().opendb();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		super.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
