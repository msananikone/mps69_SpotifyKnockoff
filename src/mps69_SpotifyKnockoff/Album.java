/**
 * class Album
 * @author Monica Sananikone mps69
 * @created 1/26/18
 */

package mps69_SpotifyKnockoff;

import java.util.Map;

public class Album {
	private String albumID;
	private String title;
	private String releaseDate;
	private String coverImagePath;
	private String recordingCompany;
	private int numberOfTracks;
	private String pmrcRating;
	private int length;
	Map <Song, Song> albumSongs;
	
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks, String pmrcRating, int length) {
		
	}
	
	public Album(String albumID) {
		
	}
	
	private void deleteAlbum(String albumID) {
		
	}
	
	private void addSong(Song song) {
		
	}
	
	private void deleteSong(String songID) {
		
	}
	
	private void deleteSong(Song song) {
		
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCoverImagePath() {
		return coverImagePath;
	}
	public void setCoverImagePath(String coverImagePath) {
		this.coverImagePath = coverImagePath;
	}
	public String getRecordingCompany() {
		return recordingCompany;
	}
	public void setRecordingCompany(String recordingCompany) {
		this.recordingCompany = recordingCompany;
	}
	public int getNumberOfTracks() {
		return numberOfTracks;
	}
	public void setNumberOfTracks(int numberOfTracks) {
		this.numberOfTracks = numberOfTracks;
	}
	public String getPmrcRating() {
		return pmrcRating;
	}
	public void setPmrcRating(String pmrcRating) {
		this.pmrcRating = pmrcRating;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Map<Song, Song> getAlbumSongs() {
		return albumSongs;
	}
	public void setAlbumSongs(Map<Song, Song> albumSongs) {
		this.albumSongs = albumSongs;
	}
	public String getAlbumID() {
		return albumID;
	}
	

}
