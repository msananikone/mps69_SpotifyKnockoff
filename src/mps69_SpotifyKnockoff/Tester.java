package mps69_SpotifyKnockoff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Tester {
	public static void main(String[] args){
		// Song s1 = new Song("Stairway to Heaven", 8.02, "1971-10-08", "1971-05-07", "57ed733c-0099-11e8-a67a-005056881e07");
		// Song s2 = new Song("Say a little prayer", 3.74, "1971-10-08", "1971-05-07", "57ed733c-0099-11e8-a67a-005056881e07");
		// Song s3 = new Song("Stairway to Heaven", 8.02, "1971-10-08", "1971-05-07");
		// Song(String title, int length, String releaseDate, String recordDate)
		// Song s4 = new Song("0d701280-cb4d-42a7-9866-770d7ad02f42");
		
		//ArrayList<Song> songList = new ArrayList<Song>();
		//Map<String, Song> songList = new Hashtable<String, Song>();
		Hashtable<String, Song> songList = new Hashtable<String, Song>();
		
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song;";
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){ //for each result that comes in, creates new object
				Song s = new Song(rs.getString("song_id"));
				songList.put(s.getSongID(), s);
				System.out.println(s.getTitle()); //returns song titles from db
			}
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		// Find a song with ID b2dc89b2-205e-40ac-be60-d9b271490458
		/*
		for(Song s : songList) {
			//linear search
			if(s.getSongID().equalsIgnoreCase("b2dc89b2-205e-40ac-be60-d9b271490458")) {
				System.out.println("Found " + s.getTitle());
				break;
			}
		}
		*/
		
		//If using Hashtable, delete above code block and run code below
		Song foundSong = songList.get("b2dc89b2-205e-40ac-be60-d9b271490458");
		System.out.println(foundSong.getTitle());
	}
}
