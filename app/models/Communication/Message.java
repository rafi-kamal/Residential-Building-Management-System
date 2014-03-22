package models;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class Message Extends play.db.ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;

	@Required
	public LocalDate date;

	public String body;

	public boolean isRead=0;

	public Message(){

	}

	public Message(LocalDate date, String body) {
		this.dat=date;
		this.body=body;
	}

	public static Finder<Long,Message> find = new Finder<Long,Chat> (Long.class, Message.class);
}