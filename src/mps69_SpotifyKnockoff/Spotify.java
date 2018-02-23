package mps69_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Spotify {
	
	private static Spotify spotify;
	
	private Spotify() {
		
	}
	
	public static Spotify getInstance() {
		if (spotify == null) {
			spotify = new Spotify();
		}
		return spotify;
	}
	
	public DefaultTableModel searchSongs(String searchString){
		String sql = "SELECT song_id, title, length, release_date, record_date FROM song ";
		if(!searchString.equals("")) {
			sql += " WHERE title LIKE '%" + searchString + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Song ID", "Title", "Length", "Release Date", "Record Date"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(SpotifyGUI.getFrame(), "Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel searchAlbums(String searchString){
		String sql = "SELECT album_id, title, release_date, cover_image_path, recording_company_name, number_of_tracks, PMRC_rating, length FROM album ";
		if(!searchString.equals("")) {
			sql += " WHERE title LIKE '%" + searchString + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Album ID", "Title", "Release Date", "Cover Image Path", "Recording Comapny", "Number of Tracks", "PMRC Rating", "Length"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(SpotifyGUI.getFrame(), "Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
	
	public DefaultTableModel searchArtists(String searchString){	
		String sql = "SELECT artist_id, first_name, last_name, band_name FROM artist ";
		if(!searchString.equals("")) {
			sql += "WHERE first_name LIKE '%" + searchString + "%' OR last_name LIKE '%" + searchString + "%' OR band_name LIKE '%" + searchString + "%';";
		}
		try {
			DbUtilities db = new DbUtilities();
			String[] columnNames = {"Artist ID", "First Name", "Last Name", "Band"};
			return db.getDataTable(sql, columnNames);
			
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(SpotifyGUI.getFrame(), "Unable to connect to database");
			ErrorLogger.log(e.getMessage());
		}
		return null;
	}
}