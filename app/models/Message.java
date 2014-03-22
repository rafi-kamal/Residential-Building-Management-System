package models;

import org.joda.time.LocalTime;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class Message extends play.db.ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;

	@Required
	public LocalTime timeStamp;

	public String body;

	public boolean isRead=0;

	public Message(){

	}

	public Message(LocalTime timeStamp, String body) {
		this.timeStamp=timeStamp;
		this.body=body;
	}

	public static Finder<Long,Message> find = new Finder<Long,Chat> (Long.class, Message.class);
}