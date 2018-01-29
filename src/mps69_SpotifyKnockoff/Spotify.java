package mps69_SpotifyKnockoff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Spotify {

	public static void main(String[] args) {
		String hostName = "sis-teach-01.sis.pitt.edu";
		String dbName = "spotify_knockoff";
		String userName = "spotifyUser";
		String password = "spotifyUser123";
		
		String connString = "jdbc:mysql://" + hostName + "/"
				+ dbName + "?user=" +userName 
				+ "&password=" +password;
		//System.out.println(connString);
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(connString);
			
			//comment this out if using INSERT sql string
			String sql = "SELECT * FROM users;";
			
			//String sql = "INSERT INTO `spotify_knockoff`.`users`\r\n" + 
			//		"(`user_id`, `first_name`, `last_name`, `email`, `password`)\r\n" + "VALUES\r\n" + 
			//		"(UUID(), 'Monica', 'S', 'mps69@pitt.edu',MD5('password4'));";
			
			Statement statement = conn.createStatement();
			//comment rs out if using INSERT sql string
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + "\t" +rs.getString(2) + "\t" + rs.getString("last_name"));
			}
			
			//comment this if NOT using INSERT sql statement
			//statement.executeUpdate(sql);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
