/**
 * class SpotifyTester
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public class SpotifyTester 
{

	public static void main(String[] args) {
		/*code from class:
		//Song s1 = new Song("Testing11", 8.02, "1971-10-08", "1971-05-07", "256d2978-056e-11e8-a67a-005056881e07");
		/*Song s2 = new Song("Testing2", 8.02, "1971-10-08", "1971-05-07", "256d2978-056e-11e8-a67a-005056881e07");
		//Song s2 = new Song("Testing3", 3.75, "1971-10-08", "1971-05-07", "256d2978-056e-11e8-a67a-005056881e07");
		
		//s4 drops the table song
		//Song s4 = new Song("e7850398-6655-4b0f-b36e-793afafb23d9\'; DROP TABLE song; #");
		
		//Song s4 = new Song("e7850398-6655-4b0f-b36e-793afafb23d9");
		
		//ArrayList<Song> songList= new ArrayList<Song>();
		*/
		//We can create hashtable or Map instead of arraylist
		
		//Hashtable<String, Song> songList= new Hashtable<String, Song>();
		
		//Use linear search block if using ArrayList
		/*
		for (Song s : songList) { //linear search block
			if(s.getSongID().equalsIgnoreCase("bbc57e3a-597d-4ce0-8f42-10d11b9e4778")) { //song: Sunday morning
				System.out.println("(ArrayList) Found: " + s.getTitle());
				break;
			}
		}
		//Use this if using hashtable
		Song foundSong = songList.get("bbc57e3a-597d-4ce0-8f42-10d11b9e4778");
		System.out.println("(Hashtable) Found: "+ foundSong.getTitle());
		*/
			
		//Storing song in hashtable..
		//Map<String, Song> songList = new Hashtable<String, Song>();
		
		/*Prints songs from db
		Vector<Vector<String>> songTable = new Vector<>();
		try {
			DbUtilities db = new DbUtilities();
			String sql = "SELECT * FROM song;";
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()) {
				Song s = new Song(rs.getString("song_id"),
						rs.getString("title"),
						rs.getDouble("length"),
						rs.getString("release_date"),
						rs.getString("record_date"));
				//songList.put(s.getSongID(), s); //use this for hashtalbe
				songTable.add(s.getSongRecord());
				System.out.println(s.getTitle());
			}
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		/*Prints the whole table of songs from DB
		for(int i=0; i<songTable.size(); i++) {
			for(int j=0; j<songTable.get(i).size(); j++) {
				System.out.print(songTable.get(i).get(j) + "\t\t\t");
			}
			System.out.println();
		}
		*/
		/*
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Create objects~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Song so1 = new Song("Blessed Be Your Name", 5.48, "2015-07-07", "2015-01-01");
		//Song so1 = new Song("a54ba89d-ac33-4ffb-abd5-d600b7abb764");
		Artist ar1 = new Artist("Matt", "Redman", "");
		//Artist ar2 = new Artist("1c89bea6-6453-41fd-a159-565883c1e07c");
		Album al1 = new Album("The Songs of Matt Redman Vol. 1", "2015-07-07", "Survivor", 12, "1", 59.34);
		Album al2;
		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Find objects~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("SongID from db: " + so1.getSongID() + "\n"
				+ "Song title from db: " + so1.getTitle() + "\n"
				+ "Song length from db: " + so1.getLength() + "\n"
				+ "Song releaseDate from db: " + so1.getReleaseDate() + "\n"
				+ "Song recordDate from db: " + so1.getRecordDate() + "\n");

		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Add objs to things~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//--------------------------addArtist(artist:Artist)------------------------------
		//System.out.println("Adding artist " + ar1.getFirstName() + " " + ar1.getLastName() + 
		//		" with fk_artist_id " + ar1.getArtistID() + " to fk_song_id " + so1.getSongID());
		//so1.addArtist(ar1);
		
		//--------------------------addSong(song:Song)-------------------------------------
		System.out.println("Adding song: " + so1.getTitle() + "\nto Album: " + al1.getTitle() + 
				"\nwith fk_song_id: " + so1.getSongID() + "\nto fk_album_id: " + al1.getAlbumID());
		al1.addSong(so1);
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Delete objects from things~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//--------------------------deleteSong(songID:String) from Song-----------------------------
		System.out.println("Deleting song " + so1.getTitle() + " with ID " + so1.getSongID());
		so1.deleteSong(so1.getSongID());
		
		//System.out.println("Deleting song " + so1.getTitle() + " with ID " + so1.getSongID());
		//so1.deleteSong("a54ba89d-ac33-4ffb-abd5-d600b7abb764");
		//--------------------------deleteArtist(artistID:String) from Song--------------------------
		System.out.println("Deleting artist " + ar1.getArtistID() + "from songArtists");
		ar1.deleteArtist(ar1.getArtistID());
		//--------------------------deleteArtist(artist:Artist) from Song----------------------------
		so1.deleteArtist(ar1);
		//--------------------------deleteArtist(artistID:String) from Artist--------------------------
		ar1.deleteArtist(ar1.getArtistID());
		//--------------------------deleteArtist(artistID:String) from Album--------------------------
		ar1.deleteArtist(ar1.getArtistID());
		//--------------------------deleteSong(songID:String) from Album------------------------------
		al1.deleteSong(so1.getSongID());
		//--------------------------deleteSong(song:Song) from Album----------------------------------
		al1.deleteSong(so1);
		//--------------------------deleteAlbum(albumID:String)
		al1.deleteAlbum(al1.getAlbumID());
		 */
		
		Artist art1 = new Artist ("1c89bea6-6453-41fd-a159-565883c1e07c");
		art1.deleteArtist(art1.getArtistID());
	}
}
