package mps69_SpotifyKnockoff;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class ErrorLogger {
	//static so we don't have to create an instance, just care about calling it
	public static void log(String errorMessage){		
		//Save the following information to errorlog.txt
		
		//Information needed: Date, Time, errorMessage \n
		//String will look like: 01/30/2018, 10:44:44 PM, Table 'spotify_knockoff.song' doesn't exist
	    String filePath = "errorlog.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true));
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy,HH:mm:ss,");
			Date date = new Date();
			bw.write(dateFormat.format(date));
			bw.write(errorMessage);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
