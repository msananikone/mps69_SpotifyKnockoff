/**
 * class Album
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private double length;
	//Map<String, Artist> songArtists;
	Map <String, Song> albumSongs;
	
	/**
	 * @constructor Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length)
	 * @param title String
	 * @param releaseDate String
	 * @param recordingCompany String
	 * @param numberOfTracks int
	 * @param pmrcRating String
	 * @param length double
	 */
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length) {
		/**
		 * Create new album record in the db
		 * Create new Album OBJECT
		 * Generate an albumID using java.util.UUID.randomUUIUD()
		 * Set corresponding class properties
		 */
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = UUID.randomUUID().toString(); //without toString, it returns an object
		
		//System.out.println(this.songID); //go to tester class and create a song
		//String sql = "INSERT INTO song (song_id,title,length,file_path,release_date,record_date,fk_album_id) "; //need trailing space so VALUES sql is called correctly
		//sql += "VALUES ('" + this.songID + "', '" + this.title + "', " + this.length + ", '', '" + this.releaseDate + "', '" + this.recordDate + "', '" + this.albumID + "');";
		
		//Trailing space is important for query to properly execute in MySQL
		String sql = "INSERT INTO album (album_id, title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length) ";
		sql += "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		
		System.out.println(sql);		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//JDBC validates input from malicious users by using PreparedStatements
			//treats entire string as a literal (not a sql statement from concatenating)
			
			ps.setString(1, this.albumID);
			ps.setString(2, this.title);
			ps.setString(3, this.releaseDate);
			ps.setString(4, coverImagePath); //cover_image_path
			ps.setString(5, this.recordingCompany);
			ps.setInt(6, this.numberOfTracks); 
			ps.setString(7, this.pmrcRating);
			ps.setDouble(8, this.length);
			ps.executeUpdate();
			
			db.closeDbConnection();
			db = null;
		} catch (SQLException e){
			e.printStackTrace();
		}	
		}
	/**
	 * @constructor Album(String albumID)
	 * @param albumID String
	 */
	public Album(String albumID) {
		albumSongs = new Hashtable<String, Song>();
		String sql = "SELECT * FROM album WHERE album_id = '" + albumID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.albumID = rs.getString("album_id");
				this.title = rs.getString("title");
				this.releaseDate = rs.getDate("release_date").toString();
				this.coverImagePath = rs.getString("cover_image_path");
				this.recordingCompany = rs.getString("recording_company_name");
				this.numberOfTracks = rs.getInt("number_of_tracks");
				this.pmrcRating = rs.getString("PMRC_rating");
				this.length = rs.getDouble("length");
				System.out.println("Album title from database: " + this.title);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @constructor Album(
	 * @param albumID String
	 * @param title String
	 * @param releaseDate String
	 * @param recordingCompany String
	 * @param numberOfTracks int
	 * @param pmrcRating String
	 * @param length double
	 */
	public Album(String albumID, String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, double length) {
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		this.pmrcRating = pmrcRating;
		this.length = length;
		this.albumID = albumID;
		albumSongs = new Hashtable<String, Song>();
	}
	/**
	 * @method deleteAlbum(String albumID)
	 * @param albumID String
	 * Deletes album object from the db
	 */
	public void deleteAlbum(String albumID) {
		String sql = "DELETE FROM album WHERE album_id = '" + albumID + "';";
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		System.out.println("Album deleted from database.");
	}
	/**
	 * @method addSong(Song song)
	 * @param song Song
	 */
	public void addSong(Song song) {
		albumSongs.put(song.getSongID(), song);
		String sql = "INSERT INTO album_song(fk_album_id, fk_song_id) VALUES (?, ?);";
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, this.albumID);
			ps.setString(2, song.getSongID());
			
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @method deleteSong(String songID)
	 * @param songID String
	 */
	public void deleteSong(String songID) {
		Song song = new Song(songID);
		albumSongs.remove(song);
	}
	/**
	 * @method deleteSong(Song song)
	 * @param song Song
	 */
	public void deleteSong(Song song) {
		albumSongs.remove(song, this.albumID);
	}
	
	//Getters
	//title, releaseDate, recordingCompany, numberOfTracks, pmrcRating, length, albumSongs, albumID
	public String getTitle() {
		return title;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getCoverImagePath() {
		return coverImagePath;
	}
	public String getRecordingCompany() {
		return recordingCompany;
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public double getLength() {
		return length;
	}
	public Map<Song, Song> getAlbumSongs() {
		return getAlbumSongs();
	}

	public String getAlbumID() {
		return albumID;
	}
	
	//Setters
	//title, releaseDate, recordingCompany, numberOfTracks, pmrcRating, length, albumSongs, coverImagePath
	public void setTitle(String title) {
		this.title = title;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}
	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setAlbumSongs(Hashtable<String, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}	
}
