/**
 * class Artist
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.UUID;

public class Artist {
	private String artistID;
	private String firstName;
	private String lastName;
	private String bandName;
	private String bio;
	Hashtable<String, Artist> songArtists;
	
	/**
	 * @constructor Artist(String firstName, String lastName, String bandName)
	 * @param firstName String
	 * @param lastName double
	 * @param bandName String
	 * @throws IOException 
	 */
	public Artist(String firstName, String lastName, String bandName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.artistID = UUID.randomUUID().toString(); //without toString, it returns an object
		
		//Trailing space is important for query to properly execute in MySQL
		String sql = "INSERT INTO artist (artist_id,first_name,last_name,band_name,bio) ";
		sql += "VALUES (?, ?, ?, ?, ?);";
		
		//System.out.println(sql); //debug		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//JDBC validates input from malicious users by using PreparedStatements
			//treats entire string as a literal (not a sql statement from concatenating)
			
			ps.setString(1, this.artistID);
			ps.setString(2, this.firstName);
			ps.setString(3, this.lastName);
			ps.setString(4, this.bandName); 
			ps.setString(5, ""); //bio
			
			ps.executeUpdate();
			db.closeDbConnection(); //close jdbc connection after done using
			db = null;
		} catch (SQLException e){
			e.printStackTrace();
			try {
				ErrorLogger.log(e.toString());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}	
	}
	
	public Artist(String artistID) {
		System.out.println("hi");
		String sql = "SELECT * FROM artist WHERE artist_id = '" + artistID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				System.out.println("Artist ID from database: " + this.artistID);
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
				this.bio = rs.getString("bio"); //if there isnt a bio, it is set as null in the db
				System.out.println("Artist from database: " + this.firstName + " " + this.lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Artist(String artistID, String firstName, String lastName, String bandName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		this.artistID = artistID;
		
		songArtists = new Hashtable<String, Artist>();
	}
	
	public void deleteArtist(String artistID) {
		//String sql = "DELETE FROM artist WHERE artist_id = ?;";
		
		String sql = "ALTER TABLE `artist`"
				+ " ADD CONSTRAINT `fk_song_has_artist_artist1` FOREIGN KEY (`fk_artist_id`) "
				+ "REFERENCES `advertisers` (`artist_id`);";
		//sql statement inspired from https://stackoverflow.com/questions/1905470/cannot-delete-or-update-a-parent-row-a-foreign-key-constraint-fails
		
		try {
			DbUtilities db = new DbUtilities();
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, artistID);
			ps.executeUpdate();
			db.closeDbConnection();
			db = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Artist deleted from db");
	}
	
	//Getters and Setters
	//make setter for bio
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getArtistID() {
		return artistID;
	}
	
	public void addArtist(Artist artist) {
		songArtists.put(artist.getArtistID(), artist);
	}
	
}
