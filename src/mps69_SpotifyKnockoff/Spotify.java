package mps69_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Spotify {

	public static void main(String[] args) {
		String hostName = "sis-teach-01.sis.pitt.edu";
		String dbName = "spotify_knockoff";
		String userName = "spotifyUser";
		String password = "spotifyUser123";
		
		String connString = "jdbc:mysql://" + hostName + "/" + dbName + "/" + "?user=" + userName + "&password=" + password;
		System.out.println(connString);
		
		try {
			Class.forName("com.mysqpl.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(connString);
			
			String sql = "SELECT * FROM users;";
			
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString("last_name"));
			}
			
			//statement.executeUpdate(sql);
			
		} catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}

}
