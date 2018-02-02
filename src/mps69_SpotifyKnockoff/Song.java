/**
 * class Song
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Song {
	private String songID;
	private String title;
	private double length;
	private String filePath;
	private String releaseDate;
	private String recordDate;
	Map <String, Artist>songArtists;
	
	/**
	 * @constructor Song(String title, double length, String releaseDate, String recordDate)
	 * @param songID String
	 * @param length double
	 * @param releaseDate String
	 * @param recordDate String
	 * To add a song that doesn't exist in the database
	 */
	
	public Song(String title, double length, String releaseDate, String recordDate){
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString(); //without toString, it returns an object		
		songArtists = new Hashtable<String, Artist>();
		
		//System.out.println(this.songID); //go to tester class and create a song
		//String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) "; //need trailing space so VALUES sql is called correctly
		//sql += "VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '', '" + this.releaseDate + "', '" + this.recordDate + "', '" + this.albumID + "');";
		
		//Trailing space is important for query to properly execute in MySQL
		String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?);";
		
		//System.out.println(sql);		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn(); //open connection
			PreparedStatement ps = conn.prepareStatement(sql);
			//JDBC validates input from malicious users by using PreparedStatements
			//treats entire string as a literal (not a sql statement from concatenating)
			
			ps.setString(1, this.songID);
			ps.setString(2, this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, ""); 
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			
			ps.executeUpdate(); //update mysql
			db.closeDbConnection(); //close jdbc connection after done using
			db = null;
		} catch (SQLException e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * @constructor Song(String songID)
	 * @param songID String
	 * To pull out an existing song from the database
	 */
	public Song(String songID){
		songArtists = new Hashtable<String, Artist>();
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		//System.out.println(sql);
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.songID = rs.getString("song_id");
				// System.out.println("Song ID from database: " + this.songID);
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.recordDate = rs.getDate("record_date").toString();
				this.length = rs.getDouble("length");
				//System.out.println("Song title from database: " + this.title);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	
	/**
	 * @constructor Song(String songID ..)
	 * instead of setting songid to something random, setting to something passed in
	 * if data already available, create songid instead of pull from db again
	 */
	public Song(String songID, String title, double length, String releaseDate, String recordDate){
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = songID; //songid that is passed in 	
	
		songArtists = new Hashtable<String, Artist>();
	
	}	
	
	/**
	 * @method deleteSong(String songID)
	 * @param songID String
	 */
	public void deleteSong(String songID) {
		String sql = "DELETE FROM song WHERE song_id = '" + songID + "';";
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
	}
	/**
	 * @method addArtist(Artist artist)
	 * @param artist Artist
	 */
	public void addArtist(Artist artist) {
		songArtists.put(artist.getArtistID(), artist);
		String sql = "INSERT INTO song_artist(fk_song_id, fk_artist_id) VALUES (?, ?);";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, this.songID);
			ps.setString(2, artist.getArtistID());
			
			ps.executeUpdate();
			conn.close();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @method deleteArtist(String artistID)
	 * @param artistID String
	 */
	public void deleteArtist(String artistID) {
		System.out.println("deleteArtist(Artist artistID): Deleting artist..");
		Artist artist = new Artist(artistID);
		songArtists.remove(artist);
		System.out.println("deleteArtist(Artist artistID): Artist deleted from " + this.title);
	}
	/**
	 * @method deleteArtist(Artist artist)
	 * @param artist Artist
	 */
	public void deleteArtist(Artist artist) {
		System.out.println("deleteArtist(Artist artist): Deleting artist..");
		songArtists.remove(artist, this.songID);
		System.out.println("deleteArtist(Artist artist): Artist deleted from " + this.title);
	}
	/**
	 * @method setFilePath(String filePath)
	 * @param filePath String string of a song's file path
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
		String sql = "UPDATE song SET file_path = ? WHERE song_id = ?";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, filePath);
			ps.setString(2, this.songID);
			
			ps.executeUpdate();
			conn.close();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @method getSongRecord()
	 */
	Vector<String> getSongRecord(){
		Vector<String> songRecord = new Vector<>(6); //6 items (title,title length filepath releasedate recorddate)
		songRecord.add(this.songID);
		songRecord.add(this.title);
		songRecord.add(this.filePath);
		songRecord.add(String.valueOf(this.length));
		songRecord.add(this.releaseDate);
		songRecord.addElement(this.recordDate);
		return songRecord;
	}
	
	//Getter and Setters
	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSongID() {
		return songID;
	}

	public String getTitle() {
		return title;
	}

	public double getLength() {
		return length;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public Map<String, Artist> getSongArtists() {
		return songArtists;
	}

	public void setSongID(String songID) {
		this.songID = songID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public void setSongArtists(Hashtable<String, Artist> songArtists) {
		this.songArtists = songArtists;
	}
}
