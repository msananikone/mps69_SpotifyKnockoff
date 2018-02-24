package mps69_SpotifyKnockoff;

import java.util.UUID;
import javax.persistence.*;

public class SongController {
	EntityManagerFactory emfactory;
	EntityManager emanager;
	
	public SongController(){
		emfactory = Persistence.createEntityManagerFactory("tpk18_SpotifyKnockoff");
		emanager = emfactory.createEntityManager();
	}
	
	public Song createSong(String title, double length, String filePath, String releaseDate, String recordDate){
		emanager.getTransaction().begin();
		Song s = new Song();
		s.setSongID(UUID.randomUUID().toString());
		s.setTitle(title);
		s.setLength(length);
		s.setReleaseDate(releaseDate);
		s.setRecordDate(recordDate);
		s.setFilePath(filePath);
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
		
		return s;
		
	}
	public Song deleteSong(String songID){
		emanager.getTransaction().begin();
		Song s = emanager.find(Song.class, songID);
		emanager.remove(s);
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
		
		return s;
	}
	public Song updateSong(String songID, String param2, String param1){
		emanager.getTransaction().begin();
		Song s = emanager.find(Song.class, songID);
		if(param2 == "title")
		{
			s.setTitle(param1);
		}else if(param2 == "length"){
			s.setLength(Double.parseDouble(param1));
		}else if(param2 == "filePath"){
			s.setFilePath(param1);
		}else if(param2 == "releaseDate"){
			s.setReleaseDate(param1);
		}else if(param2 == "recordDate"){
			s.setRecordDate(param1);
		}
		emanager.persist(s);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
		
		return s;
	}
	
	
	
}