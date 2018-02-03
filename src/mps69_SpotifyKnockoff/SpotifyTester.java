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
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Create objects~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Song so1 = new Song("Blessed Be Your Name", 5.48, "2015-07-07", "2015-01-01");
		//Song so1 = new Song("a54ba89d-ac33-4ffb-abd5-d600b7abb764");
		Artist ar1 = new Artist("Matt", "Redman", "");
		//Artist ar2 = new Artist("1c89bea6-6453-41fd-a159-565883c1e07c");
		Album al1 = new Album("The Songs of Matt Redman Vol. 1", "2015-07-07", "Survivor", 12, "1", 59.34);		
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Find objects~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		System.out.println("SongID from db: " + so1.getSongID() + "\n"
				+ "Song title from db: " + so1.getTitle() + "\n"
				+ "Song length from db: " + so1.getLength() + "\n"
				+ "Song releaseDate from db: " + so1.getReleaseDate() + "\n"
				+ "Song recordDate from db: " + so1.getRecordDate() + "\n");

		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Add objs to things~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//--------------------------addArtist(artist:Artist)------------------------------
		System.out.println("Adding artist " + ar1.getFirstName() + " " + ar1.getLastName() + 
				" with fk_artist_id " + ar1.getArtistID() + " to fk_song_id " + so1.getSongID());
		so1.addArtist(ar1);
		
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
		 
	}
}
