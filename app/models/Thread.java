package models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import play.data.*;
import play.data.validation.Constraints.*;

import javax.validation.Valid;
import javax.persistence.*;

@Entity
public class Thread extends play.db.ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;
	
	@Required
	public String category;
	
	@Required 
	public LocalDate date;
	
	public String subject="(No Subject)";
	
	@Required
	public Long senderId;
	
	@Required
	public Long receiverId;
	  
	@Valid
    @OneToMany(cascade=CascadeType.ALL)
    @OrderBy("timestamp")
    @JoinColumn(name="THREAD_ID", referencedColumnName="internal_id")
    public List<Message> items = new ArrayList<Message>();
	
	public Thread(){};
	
	public Thread(String category, LocalDate date, String subject, Long senderId, long receiverId) {
		this.category=category;
		this.date=date;
		this.subject=subject;
		this.senderId=senderId;
		this.receiverId=receiverId;
	}
	
	
}
