package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.LocalTime;

import play.data.validation.Constraints.Required;

@Entity
public class Message extends play.db.ebean.Model {

	private static final long serialVersionUID = -4075050135481563304L;

	@Id
	@GeneratedValue
	public Long internalId;

	@Required
	public LocalTime timeStamp;
	
	@Required
	public UserAccount sender;

	public String body;

	public boolean isRead=false;

	public Message(){

	}

	public Message(LocalTime timeStamp, String body, UserAccount sender) {
		this.timeStamp = timeStamp;
		this.body = body;
		this.sender = sender;
	}

	public static Finder<Long,Message> find = new Finder<Long,Message> (Long.class, Message.class);
}