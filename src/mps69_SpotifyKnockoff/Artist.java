/**
 * class Artist
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

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
	 */
	public Artist(String firstName, String lastName, String bandName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.artistID = UUID.randomUUID().toString(); //without toString, it returns an object
	}
	
	public Artist(String artistID) {
		String sql = "SELECT * FROM album WHERE album_id = '" + artistID + "';";
		DbUtilities db = new DbUtilities();
		try {
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				this.artistID = rs.getString("artist_id");
				this.firstName = rs.getString("first_name").toString();
				this.lastName = rs.getString("last_name").toString();
				this.bandName = rs.getString("band_name").toString();
				this.bio = rs.getString(bio).toString();
				System.out.println("Artist from database: " + this.firstName + " " + this.lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM artist WHERE artist_id = '" + artistID + "';";
		DbUtilities db = new DbUtilities();
		db.executeQuery(sql);
		System.out.println("Album deleted from database.");
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
