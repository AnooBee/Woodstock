package com.anup.woodstock.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.anup.woodstock.db.service.DBService;


/**
 * Session Bean implementation class RetrieveURL
 */
@PersistenceContext(name="WoodstockPC", unitName = "Woodstock")
@Stateless
@LocalBean
@Path("/url")
public class RetrieveURL {

	
	/*private static String INSERT_QUERY = "Insert into SHORT_URL_APP.URL (SHORT_URL, LONG_URL, LAST_MODIFIED_BY, LAST_MODIFIED_TM, CREATED_TM,VERSION) "+
            " values (?, ?,?, CURRENT_DATE, CURRENT_DATE, 1)";*/

	private static String dbURL = "jdbc:derby://localhost:1527/URLDB;create=true;user=snoopy;password=charlie";
	// private static String dbURL =
	// "jdbc:derby:C:\\DerbyDatabases\\MyDB;create=true;user=snoopy;password=charlie";
	
	private static String tableName = "SHORT_URL_APP.URL";
	
	private static String selectQuery = "select * from " + tableName + " where SHORT_URL= ?";
	
	// jdbc Connection
	private static Connection conn = null;
	private static PreparedStatement prepStmt = null;
	private static Statement stmt = null;
	
	//@Resource
	//DBService dbService;
	
    /**
     * Default constructor. 
     */
    public RetrieveURL() {
        // TODO Auto-generated constructor stub
    }

    
	@GET
    public String getURL(@PathParam("shortURL") String shortURL) {
		shortURL="cc";
		String longURL = "NOT_FOUND";
		System.out.println("short url..."+ shortURL);
		try {
			longURL = DBService.getURL(shortURL).toString();
			
		} catch (Exception e) {
			// TODO handle exception and return gracefully 
			e.printStackTrace();
			
		}
		
        return longURL;
    }
	
	
	
	private String selectURL_JDBC(String url) throws SQLException {
		
		String shortURL;
		String longURL = null;
		String lastChangedID;
		String lastChangedTm;
		String createdTm;
		int version;
		
		ResultSet results = null;
				
		try {
			
			prepStmt = conn.prepareStatement(selectQuery);
			
			prepStmt.setString(1, url);
			
			results = prepStmt.executeQuery();
			
			while (results.next()) {
				
				shortURL = results.getString(1);
				longURL = results.getString(2);
				lastChangedID = results.getString(3);
				lastChangedTm = results.getDate(4).toString();
				createdTm = results.getDate(5).toString();
				version = results.getInt(6);
				
				System.out.println(shortURL + "\t\t" + longURL + "\t" + lastChangedID + "\t\t" + lastChangedTm + 
										"\t\t" + createdTm + "\t" + version);
			}
			
			
		
		} catch (SQLException sqlExcept) {
			
			sqlExcept.printStackTrace();
			//TODO make own exception
			throw sqlExcept; 
			
		} finally {
			
			try {
				results.close();
				
				prepStmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return longURL;
	}
	
	
	
	
	
}
