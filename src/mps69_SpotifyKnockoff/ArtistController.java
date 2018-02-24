package mps69_SpotifyKnockoff;

import java.util.UUID;
import javax.persistence.*;

public class ArtistController {
	
	EntityManagerFactory emfactory;
	EntityManager emanager;
	
	public ArtistController(){
		emfactory = Persistence.createEntityManagerFactory("mps69_SpotifyKnockoff");
		emanager = emfactory.createEntityManager();
	}
	public Artist createArtist(String firstName, String lastName, String bandName, String bio){
		emanager.getTransaction().begin();
		Artist a = new Artist();
		//a.setArtistID(UUID.randomUUID().toString());
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setBandName(bandName);
		a.setBio(bio);
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
		
		return a;
	}
	public Artist deleteArtist(String artistID){
		emanager.getTransaction().begin();
		Artist a = emanager.find(Artist.class, artistID);
		emanager.remove(a);
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
		
		return a;
	}
	public Artist updateArtist(String artistID, String param2, String param1){
		emanager.getTransaction().begin();
		Artist a = emanager.find(Artist.class, artistID);		
		if(param2 == "firstName"){
			a.setFirstName(param1);
		}else if(param2 == "lastName"){
			a.setLastName(param1);
		}else if(param2 == "bandName"){
			a.setBandName(param1);
		}else if(param2 == "bio"){
			a.setBio(param1);
		}
		emanager.persist(a);
		emanager.getTransaction().commit();
		
		emanager.close();
		emfactory.close();
		
		return a;
	}
}