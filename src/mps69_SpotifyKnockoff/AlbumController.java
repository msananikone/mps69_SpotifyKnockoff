package mps69_SpotifyKnockoff;

import java.util.UUID;
import javax.persistence.*;

public class AlbumController {
	
	EntityManagerFactory emfactory;
	EntityManager emanager;
	
	public AlbumController(){
		emfactory = Persistence.createEntityManagerFactory("mps69_SpotifyKnockoff");
		emanager = emfactory.createEntityManager();
	}
	public Album createAlbum(String title, String releaseDate, String coverImagePath, String recordingCompany, int numberOfTracks, String pmrcRating, double length){
		emanager.getTransaction().begin();
		Album a = new Album();
		//a.setAlbumID(UUID.randomUUID().toString());
		a.setTitle(title);
		a.setReleaseDate(releaseDate);
		a.setCoverImagePath(coverImagePath);
		a.setRecordingCompany(recordingCompany);
		a.setNumberOfTracks(numberOfTracks);
		a.setPmrcRating(pmrcRating);
		a.setLength(length);
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close(); //be sure to close connections
		emfactory.close();
		
		return a;
	}
	public Album deleteAlbum(String albumID){
		emanager.getTransaction().begin();
		Album a = emanager.find(Album.class, albumID);
		emanager.remove(a);
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close(); //be sure to close connections
		emfactory.close();
		
		return a;
	}
	public Album updateAlbum(String albumID, String parameterToChange, String param){
		emanager.getTransaction().begin();
		Album a = emanager.find(Album.class, albumID);		
		if(parameterToChange == "title"){
			a.setTitle(param);
		}else if(parameterToChange == "releaseDate"){
			a.setLength(Double.parseDouble(param));
		}else if(parameterToChange == "coverImagePath"){
			a.setCoverImagePath(param);
		}else if(parameterToChange == "recordingCompany"){
			a.setRecordingCompany(param);
		}else if(parameterToChange == "numberOfTracks"){
			a.setNumberOfTracks(Integer.parseInt(param));
		}else if(parameterToChange == "pmrcRating"){
			a.setPmrcRating(param);
		}else if(parameterToChange == "length"){
			a.setLength(Double.parseDouble(param));
		}
		
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close(); //be sure to close connections
		emfactory.close();
		
		return a;
	}
}