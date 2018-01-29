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
	private String albumID;
	Hashtable<String, Artist> songArtists;
	//Map <String, Artist>songArtists;
	
	/**
	 * @constructor Song(String title, double length, String releaseDate, String recordDate, String albumID)
	 * @param songID String
	 * @param length double
	 * @param releaseDate String
	 * @param recordDate String
	 * @param albumID String
	 * To add a song that doesn't exist in the database
	 */
	public Song(String title, double length, String releaseDate, String recordDate, String albumID){
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		this.songID = UUID.randomUUID().toString(); //without toString, it returns an object
		this.albumID = albumID;
		
		// System.out.println(this.songID); //go to tester class and create a song
		// String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) "; //need trailing space so VALUES sql is called correctly
		// sql += "VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '', '" + this.releaseDate + "', '" + this.recordDate + "', '" + this.albumID + "');";
		
		
		String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		System.out.println(sql);
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//jdbc validates input from malicious by using preparedstatements
			//treats entire string as a literal (not a sql statement from concatenating)
			
			ps.setString(1, this.songID);
			ps.setString(2,  this.title);
			ps.setDouble(3, this.length);
			ps.setString(4, ""); 
			ps.setString(5, this.releaseDate);
			ps.setString(6, this.recordDate);
			ps.setString(7, this.albumID);
			ps.executeUpdate();
			
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @constructor Song(String songID)
	 * @param songID String
	 * To pull out an existing song from the database
	 */
	public Song(String songID){
		String sql = "SELECT * FROM song WHERE song_id = '" + songID + "';";
		// System.out.println(sql);
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
				this.albumID = rs.getString("fk_album_id");
				// System.out.println("Song title from database: " + this.title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

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

	public String getAlbumID() {
		return albumID;
	}

	public Hashtable<String, Artist> getSongArtists() {
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
