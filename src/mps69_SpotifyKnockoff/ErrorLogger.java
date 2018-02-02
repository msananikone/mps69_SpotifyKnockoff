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
	public static void log(String errorMessage) throws IOException{		
		//Save the following information to errorlog.txt
		
		//Information needed: Date, Time, errorMessage \n
		//String will look like: 01/30/2018, 10:44:44 PM, Table 'spotify_knockoff.song' doesn't exist
	    
		/*Logger logger = Logger.getLogger("Spotify Error Log");  
	    FileHandler fh;
	    
    	String fileName = "errorlogger.txt";
	    try
	    {
	        fileName = "errorlogger.txt";
	        FileWriter fw = new FileWriter(fileName,true); //the true will append the new data
	        fh = new FileHandler(fileName);  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  //formats message
	        logger.info("Testing");
	        fw.write(errorMessage + "\n");//appends the string to the file
	        fw.close();
	    }
	    catch(IOException e)
	    {
	    	fileName = "errorlogger.txt";
	    	PrintWriter pw =  new PrintWriter(fileName);
	    	FileWriter fw = new FileWriter(fileName,true);
	    	pw = new PrintWriter (fw);
	    	e.printStackTrace (pw);
	    }
		    
		    */
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("errorlog.txt", true));
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
