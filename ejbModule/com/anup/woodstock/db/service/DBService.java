package com.anup.woodstock.db.service;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

//import com.google.common.hash.Hashing;
import com.anup.woodstock.db.model.Url;


//@Resource(name = "dbService", type = javax.sql.DataSource.class, shareable = true, authenticationType = AuthenticationType.CONTAINER)
public class DBService {

	//private static final String PERSISTENCE_UNIT_NAME = "Woodstock";
	//private static EntityManagerFactory factory;

	//@PersistenceUnit(unitName ="Woodstock")
	private static EntityManagerFactory factory;
	
	@PersistenceContext(unitName = "Woodstock", type = PersistenceContextType.EXTENDED)
	private static EntityManager em;
	
	public static void main (String[] args) {
		
		DBService dbService = new DBService();
		
		Shortener shorten = new Shortener();
		String value = shorten.encode(2147483647);
		System.out.println("Value..."+ value);
		
		System.out.println("Key..."+ shorten.decode("c8nhp8j"));
		//dbService.createLongUrl("www.google.com", "AnupV");
		
		/*List <Url>urlList = dbService.getAllURLs();
		
		for (Url url : urlList) {
		
			System.out.println(url.toString());
		}
		
		String longUrl = dbService.getURL("abc123").getLongUrl();
		System.out.println("LongURL: "+ longUrl);*/
	}
	
	public static List<Url> getAllURLs() {

		//factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		//EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		
		List urlList = null;
		
		try {
		
			// obtain the initial JNDI context
            Context initCtx = new InitialContext();
            EntityManager em =  (EntityManager) initCtx.lookup("java:comp/env/Woodstock");
			Query q = em.createNamedQuery("Url.findAll");
			
			urlList = q.getResultList();
			/*for (Url url : urlList) {
				System.out.println(url);
			}*/
			System.out.println("Size: " + urlList.size());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Return the typed list
		return (List<Url>) urlList;

	}

	public static Url getURL(String shortURL) {

		//factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		//EntityManager em = factory.createEntityManager();
		// Read the existing entries and write to console
		/*
		 * Query q =
		 * em.createQuery("select URL from URL url where short_url = ?1");
		 * q.setParameter(1, shortUrl);
		 */
		Url url = null; 
		try {
			
			// obtain the initial JNDI context
            Context initCtx = new InitialContext();
            NamingEnumeration<NameClassPair> list = initCtx.list("jdbc");
            while (list.hasMore()) {
            	System.out.println("listing namevalue pairs...");
              System.out.println(list.next().getName());
            }
            EntityManager em =  (EntityManager) initCtx.lookup("java:comp/env/WoodstockPC");
		
			// Finding by primary key
			url = em.getReference(Url.class, shortURL);
	
			System.out.println("URL: " + url.toString());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return url;

	}
	

	public static void createUrl(String longURL, String lastModifiedBy) {

		//factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();

		// Create new Url
		em.getTransaction().begin();
		Url url = new Url();

		String shortUrl = createShortURL();

		url.setShortUrl(shortUrl);
		url.setLongUrl(longURL);
		url.setLastModifiedBy(lastModifiedBy);

		url.setLastModifiedTm(new Date(System.currentTimeMillis()));

		em.persist(url);
		em.getTransaction().commit();

		em.close();

	}

	public static String createShortURL() {
		//factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
//		values NEXT VALUE for short_url_app.url_seq
		Query q = em.createNativeQuery("values NEXT VALUE for short_url_app.url_seq");
		
		int id = (int) q.getSingleResult();
		
		System.out.println("DBService... id: "+ id);
		String shortURL = Shortener.encode(id);
		
		System.out.println("DBService... shortURL: "+ shortURL);
		//Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
		return shortURL;
	}

}