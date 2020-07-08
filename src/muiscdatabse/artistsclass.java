package muiscdatabse;

import javafx.beans.property.SimpleStringProperty;

public class artistsclass {
 
	 
	private String name;

	public String getName() {
		return name.toString();
	}

	public void setName(String string) {
		SimpleStringProperty simple=new SimpleStringProperty(string);
		this.name = string;
	}
	
	
	
	 
}
