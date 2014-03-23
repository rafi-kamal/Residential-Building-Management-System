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
	Long internalId;
	
	@Required
	String category;
	
	@Required
	String subject="(No Subject)";
	
	@Required
	LocalDate publishDate;
	
	@Required
	Date validUntil;
	
	@Required 
	String description;
	
	@Required
	Long publishedBy;
	
	public Notice(){};

	public Notice(String category, String subject, LocalDate publishDate,
			Date validUntil, String description) {
		this.category = category;
		this.subject = subject;
		this.publishDate = publishDate;
		this.validUntil = validUntil;
		this.description = description;
	}
	
	public static Finder<Long, Notice> find = new Finder<Long, Notice> (Long.class, Notice.class);
	
}
