package models;

import java.util.Date;

import javax.persistence.*;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;

import org.joda.time.LocalDate;

import play.data.validation.Constraints.Required;

@Entity 
public class Notice extends play.db.ebean.Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	public Long internalId;
	
	@Required
	public String category;
	
	@Required
	public String subject="(No Subject)";
	
	public LocalDate publishDate = LocalDate.now();
	
	public Date validUntil;
	
	@Required 
	@MaxLength(2048)
	@MinLength(10)
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
