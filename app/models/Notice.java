package models;

import java.sql.*;

import org.joda.time.LocalDate;

import play.data.*;
import play.data.validation.Constraints.*;

import javax.validation.Valid;
import javax.persistence.*;

@Entity 
public class Notice extends play.db.ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;
	
	@Required
	public String category;
	
	@Required
	public String subject="(No Subject)";
	
	public LocalDate publishDate = LocalDate.now();
	
	@Required
	public Date validUntil;
	
	@Required 
	public String description;
	
	@ManyToOne
	public UserAccount publishedBy;
	
	public int viewcount=0;
	
	public Notice() {};

	public Notice(String category, String subject, LocalDate publishDate,
			Date validUntil, String description, UserAccount publishedBy) {
		this.category = category;
		this.subject = subject;
		this.publishDate = publishDate;
		this.validUntil = validUntil;
		this.description = description;
		this.publishedBy = publishedBy;
	}
	
	public static Finder<Long, Notice> find = new Finder<Long, Notice> (Long.class, Notice.class);
	
}
