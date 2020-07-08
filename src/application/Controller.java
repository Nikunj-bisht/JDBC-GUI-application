package application;

import java.util.List;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import muiscdatabse.Operationclass;
import muiscdatabse.artistsclass;
import muiscdatabse.albumsbyartist;
public class Controller {
	
	@FXML 
	
	private TableView artistTable;
	
	@FXML
	
	private ProgressBar progressbar;
	 
	public void listartist() {
		
	   Task<ObservableList<artistsclass>> task=new artisttask(); 
		artistTable.itemsProperty().bind(task.valueProperty());
		//ObservableList<artistsclass> observe=FXCollections.observableArrayList(Operationclass.getinstanceofOperationclass().getartists());
	//	artistTable.setItems(observe);
		
		progressbar.progressProperty().bind(task.progressProperty());
		progressbar.setVisible(true);
		
		task.setOnSucceeded(e-> progressbar.setVisible(false));
		
		
		new Thread(task).start();
	}
	
	
	public void showselectedartistalbums() {
		
		
		artistsclass artist=   (artistsclass) artistTable.getSelectionModel().getSelectedItem();
		Task<ObservableList<albumsbyartist>> task=new Task<ObservableList<albumsbyartist>>() {

	@Override
		protected ObservableList<albumsbyartist> call() throws Exception {
				// TODO Auto-generated method stub
		return FXCollections.observableArrayList(Operationclass.getinstanceofOperationclass().getcompletelistofalbums(artist.getName()));

			}
			   
		   };

		artistTable.itemsProperty().bind(task.valueProperty());
		        
		
		new Thread(task).start();
	}
	

}


  class artisttask extends Task{
	@Override
public ObservableList<artistsclass> call() throws Exception {
		// TODO Auto-generated method stub
		return FXCollections.observableArrayList(Operationclass.getinstanceofOperationclass().getartists());
}   
	   	   
   
   
   }
//  
//

