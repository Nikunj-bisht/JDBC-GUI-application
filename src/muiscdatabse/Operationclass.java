package muiscdatabse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
 
 
public class Operationclass {
					 public static final String databaselocation="jdbc:sqlite:F:\\music.db"; 
					 public static final String tablealbum="songs";
					 public static final String album="albums";
				  	 public static final String artist="artists";  
					 
					 
					 
	public static final String INSERT_ARTIST="INSERT INTO artists(name) VALUES(?)";
	public static final String INSERT_ALBUM="INSERT INTO albums(name,artist) VALUES(?,?)";     
	public static final String INSERT_SONG="INSERT INTO songs(track,title,album) VALUES(?,?,?)";
	
	 
				   private PreparedStatement intsertintoartist;
				   private PreparedStatement intsertintoalbums;
				   private PreparedStatement intsertintosongs;
	      
    private static final String sqlquerytocheckforartist="SELECT artists._id FROM artists WHERE artists.name = ?";
    private static final String sqlquerytocheckforalbum="SELECT albums._id FROM albums WHERE albums.name = ?";
	   
	          

private PreparedStatement queryforartist;	    
private PreparedStatement queryforalbum;	

private static Operationclass instance=new Operationclass();


private Operationclass() {
   	 
	
	 
}
public  static Operationclass getinstanceofOperationclass() {
	
	
	return instance;
}

	  public void opendb() {
	   try(Connection connection=DriverManager.getConnection(databaselocation)){
	    	
  	     Statement statement =connection.createStatement();
  	// statement.execute();

  	
  }catch(SQLException e) {
         	e.printStackTrace();
  }
 
	  }
	  
	  
	  public List<artistsclass> getartists(){
		  
		  
		  List<artistsclass> list=new ArrayList<artistsclass>();
		  
		  try(Connection connection=DriverManager.getConnection(databaselocation);
				  
				  
				Statement statement= connection.createStatement()){
			  
			     ResultSet result=statement.executeQuery("Select * FROM artists");
			  
			 while(result.next()) {
					Thread.sleep(20);

				 artistsclass artist=new artistsclass();
				 
				 artist.setName(result.getString(2));
				 
				 list.add(artist);
				 
			 }
			     
			     
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  
		  
		  
		  
		  return list;
		  
		  
	  }
	  
	  
	  
	  
	  
	  public List<songaristandalbum> getcompletedetails() {
		  
		  List<songaristandalbum> completedetails=new ArrayList<songaristandalbum>();
		  
   try(Connection connection=DriverManager.getConnection(databaselocation);
		   Statement statement=connection.createStatement()){
	   
	   ResultSet result=statement.executeQuery("SELECT songs.title,albums.name,artists.name FROM "+tablealbum+" INNER JOIN albums ON "
	                              +tablealbum+".album=albums._id INNER JOIN artists ON albums.artist=artists._id"); 
	    
	    while(result.next()) {
	    	
	    	    songaristandalbum detail=new  songaristandalbum();
	    	    detail.setsongTitle(result.getString(1));
	    	    detail.setAlbumname(result.getString(2));
	    	    detail.setArtistname(result.getString(3));
	    	
	    	 completedetails.add(detail);
	    	
	    }
	statement.close();
	connection.close();
   } catch (SQLException e) {
	// TODO Auto-generatd catch block
	e.printStackTrace();
}
  return completedetails;
  
	  }
	  
	
	
	public List<albumsbyartist> getcompletelistofalbums(String albumname){
		
		
		List<albumsbyartist> list=new ArrayList<albumsbyartist>();
		
		
		try(Connection connection=DriverManager.getConnection(this.databaselocation);
				
			Statement statement=connection.createStatement()){
//			 SELECT albums.name FROM albums INNER JOIN artists ON albums.artist=artists._id WHERE artists.name='Beatles';
				ResultSet result=statement.executeQuery("SELECT "+this.album+".name FROM "+this.album+" INNER JOIN artists ON "
                        +this.album+".artist=artists._id WHERE artists.name='"+albumname+"'");
				
				while(result.next()) {
					albumsbyartist newobject=new albumsbyartist();
					newobject.setName(result.getString("name"));
					list.add(newobject);
				}
				
				
		}catch(Exception e) {
			e.printStackTrace();
		}

		
		return list;
		
	}

	
	
	  public void displayartist() {
		  
		  try(Connection connection=DriverManager.getConnection(this.databaselocation);
				  
				Statement statement=connection.createStatement();
				  
				  ResultSet result=statement.executeQuery("SELECT * FROM "+this.artist)){
			  
			  while(result.next()) {
				  System.out.println(result.getString("name"));
				  
			  }
			  statement.close();
			  connection.close();
		  }catch(Exception e) {
			  
			  e.printStackTrace();
		  }

	  }
	 public int getcount() {

		 
		 try(Connection connection=DriverManager.getConnection(databaselocation);
				 Statement statement=connection.createStatement();
				 
				 ResultSet result=statement.executeQuery("SELECT COUNT(*) FROM "+this.tablealbum)){
	 return result.getInt(1);

		 }
		 
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 
		   
		 return 0;
	 }
	 public int insertnewartist(String name)throws IOException, SQLException {
		 
		 Connection connection=DriverManager.getConnection(this.databaselocation);
		 
		this.intsertintoartist=connection.prepareStatement(this.INSERT_ARTIST,Statement.RETURN_GENERATED_KEYS); // for inserting //
		
	 
		  this.queryforartist=connection.prepareStatement(this.sqlquerytocheckforartist); // for checking if record is present or not // 
		   
		   
		  this.queryforartist.setString(1, name); 
		  
		  
		ResultSet result=this.queryforartist.executeQuery(); // execute the search query // 
		    
		 if(result.next()) {
			 return result.getInt(1); // returnsa the id if the artist is already present // 
		 }
		 else {
			 this.intsertintoartist.setString(1, name);    // create new artist //  
	    int insert= this.intsertintoartist.executeUpdate();
			 System.out.println(insert);
			try{
				ResultSet generatekey=this.intsertintoartist.getGeneratedKeys();
				System.out.println(generatekey);
				if(generatekey.next()) {
						return generatekey.getInt(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
				
			return 1;		 
		 }
		
	 }
	 public int insertnewalbum(String name,int artistid)throws IOException,SQLException {
		 
		
	  Connection connection=DriverManager.getConnection(this.databaselocation); 
		 
		   this.intsertintoalbums=connection.prepareStatement(this.INSERT_ALBUM,Statement.RETURN_GENERATED_KEYS);
		   
		   
				  this.queryforalbum=connection.prepareStatement(this.sqlquerytocheckforalbum);
				  
						 this.queryforalbum.setString(1, name);
								ResultSet result  = this.queryforalbum.executeQuery();
						 
				       
		 if(result.next()) {
			 
			 return result.getInt(1);
		 }
		 else {
			 
			     this.intsertintoalbums.setString(1, name);
			 this.intsertintoalbums.setInt(2, artistid);
			     ResultSet newresult=this.intsertintoalbums.getGeneratedKeys();
			 if(newresult.next()) {
				 
				 return newresult.getInt(1);
			 }
			 
			 
		 }

		 return 1;
	 }
	 
	 public void insertnewsong(String name,String artist,String album,int track) throws SQLException {
			Connection connection=DriverManager.getConnection(this.databaselocation);
			       
		 try {
			 	   connection.setAutoCommit(false);

			       int newartistid=insertnewartist(artist);
System.out.println(newartistid);
			       int newalbumid=insertnewalbum(album, newartistid);
System.out.println(newalbumid);
				   this.intsertintosongs=connection.prepareStatement(INSERT_SONG);

				   this.intsertintosongs.setInt(1, track);
				   this.intsertintosongs.setString(2, name);
				   this.intsertintosongs.setInt(3, newalbumid);
			int rowadded=this.intsertintosongs.executeUpdate();	   
				   if(rowadded==1) {
					   
					   connection.commit();
					   
				   }

		 else {
			 
		    throw new SQLException();
			 
			 
		 }
				   
		 }catch(Exception e)
		 {
			 e.printStackTrace();
			 try {
				 connection.rollback();
			 }catch(Exception p) {
				 p.printStackTrace();
			 }
		 }finally {
			 try {
				 System.out.println("RESETING AUTOCOMMIT");
			         connection.setAutoCommit(true);
				 
			 }catch(Exception e2) {
				 e2.printStackTrace();
			 }
		 }
		 
		 
		 
		 
	 }
	 

	  
	  
}

//
//this.intsertintoalbums=connection.prepareStatement(this.INSERT_ALBUM,Statement.RETURN_GENERATED_KEYS);
//this.intsertintosongs=connection.prepareStatement(this.INSERT_SONG);
//		  this.queryforalbum=connection.prepareStatement(this.sqlquerytocheckforalbum);







