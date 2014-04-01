package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.joda.time.LocalTime;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

@Entity
public class Message extends play.db.ebean.Model {

	private static final long serialVersionUID = -4075050135481563304L;

	@Id
	@GeneratedValue
	public Long internalId;

	@Required
	public LocalTime time = LocalTime.now();
	
	@MaxLength(1024)
	public String body;

	public boolean isRead=false;
	
	@ManyToOne
	public UserAccount sender;

	public Message(){

	}

	public Message(LocalTime time, String body, UserAccount sender) {
		this.time=time;
		this.body=body;
		this.sender=sender;
	}

	public static Finder<Long,Message> find = new Finder<Long,Message> (Long.class, Message.class);
}