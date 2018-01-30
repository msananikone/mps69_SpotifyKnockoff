/**
 * class Artist
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.util.Hashtable;

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
		
	}
	
	public Artist(String artistID) {
		
	}
	
	public void deleteArtist(String artistID) {
		
	}
	
	//Getters and Setters
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
