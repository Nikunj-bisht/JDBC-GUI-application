package muiscdatabse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Musicdb {

	
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub

		 
		 Operationclass database=new Operationclass();
		 
		 //database.opendb();
		 
//	 List<songaristandalbum> list=database.getcompletedetails();
//	     for(songaristandalbum obj:list) {
//		   
//		   System.out.println(obj.getArtistname()+"    "+obj.getAlbumname()+"  "+obj.getsongTitle());
//		   
//		   
//		   }
//       System.out.println(database.getcount());
//       
//       
//       database.displayalbums();
//      
//          System.out.println("  Enter the album name   ");
//             
//            String albumname=sc.next(); 
//           
//             List<songofalbums>list=database.getcompletelistofsongs(albumname);
//             System.out.println(list);
//
//for(songofalbums obj:list) {
//	
//	System.out.println(obj.getSongname());
//}
          

		   
		database.insertnewsong("Trampoline", "Zayn malik", "Tramp", 1);
   
     
       database.displayartist();
       
       
       
       
       
	}

}
