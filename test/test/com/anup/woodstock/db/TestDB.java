package test.com.anup.woodstock.db;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.anup.woodstock.db.model.Url;
import com.anup.woodstock.db.service.DBService;
import com.anup.woodstock.db.service.Shortener;

public class TestDB {

	private static final String PERSISTENCE_UNIT_NAME = "Woodstock";
	private static EntityManagerFactory factory;
	
	public static void main (String[] args) {
		
		
		TestDB test = new TestDB();
		
		test.createUrl("www.google.com/gooblydock", "av");
		
		/*DBService dbService = new DBService();
		
		Shortener shorten = new Shortener();
		String value = shorten.encode(2147483647);
		System.out.println("Value..."+ value);
		
		System.out.println("Key..."+ shorten.decode("c8nhp8j"));*/
		//dbService.createLongUrl("www.google.com", "AnupV");
		
		/*List <Url>urlList = dbService.getAllURLs();
		
		for (Url url : urlList) {
		
			System.out.println(url.toString());
		}
		
		String longUrl = dbService.getURL("abc123").getLongUrl();
		System.out.println("LongURL: "+ longUrl);*/
	}


	public static void createUrl(String longURL, String lastModifiedBy) {
	
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
	
		// Create new Url
		em.getTransaction().begin();
		Url url = new Url();
	
		String shortUrl = DBService.createShortURL();
	
		url.setShortUrl(shortUrl);
		url.setLongUrl(longURL);
		url.setLastModifiedBy(lastModifiedBy);
	
		url.setLastModifiedTm(new Date(System.currentTimeMillis()));
		url.setCreatedTm(new Date (System.currentTimeMillis()));
		em.persist(url);
		em.getTransaction().commit();
	
		em.close();
	
	}



}
