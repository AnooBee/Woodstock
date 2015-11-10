package com.anup.woodstock.db.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


//TODO - implement default timestamp here for creation and updating times
// use base class approach?
//http://blog.octo.com/en/audit-with-jpa-creation-and-update-date/

/**
 * The persistent class for the URL database table.
 * 
 */

@Entity
@NamedQuery(name="Url.findAll", query="SELECT u FROM Url u")
public class Url implements Serializable {
	private static final long serialVersionUID = 1L; 

	/*@Id
	@TableGenerator(name="TABLE_GEN", table="SEQUENCE_TABLE", pkColumnName="SEQ_NAME",
    valueColumnName="SEQ_COUNT", pkColumnValue="URL_SEQ")
	@GeneratedValue(strategy=GenerationType.TABLE, generator="TABLE_GEN")*/
	
	
	@SequenceGenerator(name="Url_Gen", sequenceName="Url_Seq")
	@Id @GeneratedValue(generator="Url_Gen") 
	@Column(updatable=false, unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_TM", nullable= false)
	private Date createdTm;

	@Column(name="LAST_MODIFIED_BY", nullable=false, length=256)
	private String lastModifiedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="LAST_MODIFIED_TM")
	private Date lastModifiedTm;

	@Column(name="LONG_URL")
	private String longUrl;

	@Column(name="SHORT_URL")
	private String shortUrl;

	@Version
	private int version;

	public Url() {
	}

	public int getId() {
		return this.id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public Date getCreatedTm() {
		return this.createdTm;
	}

	public void setCreatedTm(Date createdTm) {
		this.createdTm = createdTm;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedTm() {
		return this.lastModifiedTm;
	}

	public void setLastModifiedTm(Date lastModifiedTm) {
		this.lastModifiedTm = lastModifiedTm;
	}

	public String getLongUrl() {
		return this.longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortUrl() {
		
		//return Shortener.encode(this.getId());
		return this.shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		System.out.println("ID..."+ this.getId());
		//this.shortUrl = Shortener.encode(this.getId());
		System.out.println("Shorturl..."+shortUrl);
		this.shortUrl = shortUrl;
	}

	public int getVersion() {
		return this.version;
	}


	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", createdTm=" + createdTm
				+ ", lastModifiedBy=" + lastModifiedBy + ", lastModifiedTm="
				+ lastModifiedTm + ", longUrl=" + longUrl + ", shortUrl="
				+ shortUrl + ", version=" + version + "]";
	}

}