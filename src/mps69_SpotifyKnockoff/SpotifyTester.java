/**
 * class SpotifyTester
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class SpotifyTester 
{

	public static void main(String[] args) {
		//Song s1 = new Song("Testing1", 8.02, "1971-10-08", "1971-05-07", "256d2978-056e-11e8-a67a-005056881e07");
		//Song s2 = new Song("Testing2", 8.02, "1971-10-08", "1971-05-07", "256d2978-056e-11e8-a67a-005056881e07");
		Song s2 = new Song("Testing3", 3.75, "1971-10-08", "1971-05-07", "256d2978-056e-11e8-a67a-005056881e07");
		
		//s4 drops the table song
		//Song s4 = new Song("e7850398-6655-4b0f-b36e-793afafb23d9\'; DROP TABLE song; #");
		
		//Song s4 = new Song("e7850398-6655-4b0f-b36e-793afafb23d9");
		
		//ArrayList<Song> songList= new ArrayList<Song>();
		
		//We can create hashtable or Map instead of arraylist
		Hashtable<String, Song> songList= new Hashtable<String, Song>();
		//Map<String, Song> songList = new Hashtable<String, Song>();

		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song;";
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				Song s = new Song(rs.getString("song_id"));
				songList.put(s.getSongID(), s); //use this for hashtalbe
				//songList.add(s); //uncomment this if using ArrayList<Song>..
				System.out.println(s.getTitle());
			}
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* //Use linear search block if using ArrayList
		for (Song s : songList) { //linear search block
			if(s.getSongID().equalsIgnoreCase("bbc57e3a-597d-4ce0-8f42-10d11b9e4778")) { //song: Sunday morning
				System.out.println("(ArrayList) Found: " + s.getTitle());
				break;
			}
		}
		*/
		
		//Use this if using hashtable
		Song foundSong = songList.get("bbc57e3a-597d-4ce0-8f42-10d11b9e4778");
		System.out.println("(Hashtable) Found: "+foundSong.getTitle());
		
	}

}
