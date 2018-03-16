package mps69_SpotifyKnockoff;

public class SpotifyJPATester {

	public static void main(String[] args) {
		SongController song = new SongController();
		ArtistController artist = new ArtistController();
		AlbumController album = new AlbumController();

		System.out.println("Start testing");
		
		System.out.println("Creating a new Song");
		Song s1 = song.createSong("Monica's Song", 7, "", "02/22/2018", "02/22/2018");
		
		System.out.println("Creating a new Song to be deleted");
		Song s2 = song.createSong("Monica's 2nd Song", 2.5, "", "02/01/2018", "02/01/2018");
		
		System.out.println("The two songs created were: " + s1.getTitle() + " " + s2.getTitle());
		
		System.out.println("Updating Song: " + s1.getTitle());
		song.updateSong(s1.getSongID(), "Monica's Song remix", "I changed!");
		System.out.println("Song updated: " + s1.getTitle());
		
		System.out.println("Deleting Song: " + s2.getTitle());
		song.deleteSong(s2.getSongID());
		System.out.println("Song deleted: " + s2.getTitle());
		
		System.out.println("Creating a new Album");
		Album a1 = album.createAlbum("Bitter Strange", "05/08/2017", "", "Universal", 10, "PG-13", 35);
		
		System.out.println("Creating a new Album to be deleted");
		Album a2 = album.createAlbum("The Afternoon", "04/21/2016", "", "Sony Records", 8, "PG-13", 40);
		
		System.out.println("2 new Albums were created " + a2.getTitle() + " " + a2.getTitle());
		
		System.out.println("Updating Album: " + a1.getTitle());
		album.updateAlbum(a1.getAlbumID(), "title", "I changed!");
		System.out.println("Album updated: " + a1.getTitle());
		
		System.out.println("Deleting Album: " + a2.getTitle());
		album.deleteAlbum(a2.getAlbumID());
		System.out.println("Album deleted: " + a2.getTitle());
		
		
		System.out.println("Creating a new Artist");
		Artist ar1 = artist.createArtist("Michael", "Jackson", "Jackson5", "here lies fluffy, she was loved");
		
		System.out.println("Creating a new Album to be deleted");
		Artist ar2 = artist.createArtist("Katy", "perry", "Katy Perry", "I'm like, really cool");
		
		System.out.println("2 new Artists were created "+ ar1.getFirstName()+" "+ ar1.getLastName()+" "+ ar2.getFirstName()+" "+ ar2.getLastName());
		
		System.out.println("Updating Artist: "+ ar1.getFirstName()+" "+ ar1.getLastName());
		artist.updateArtist(ar1.getArtistID(), "bio", "I changed my bio!");
		System.out.println("Artist updated: "+ ar1.getFirstName()+" "+ ar2.getLastName());
		
		System.out.println("Deleting Artist: "+ ar2.getFirstName()+" "+ ar2.getLastName());
		artist.deleteArtist(ar2.getArtistID());
		System.out.println("Artist deleted: "+ ar2.getFirstName()+" "+ ar2.getLastName());


	}

}