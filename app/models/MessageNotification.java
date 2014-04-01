package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

@Entity
public class MessageNotification extends Notification {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@Required
	public Message message;
	
	public static Finder<Long, MessageNotification> find = 
			new Finder<Long, MessageNotification> (Long.class, MessageNotification.class);
	
	@Override
	public String getNotificationMessage() {
		return "You have a new message from " + message.sender.name
				+ "; sent at " + message.time;
	}
}
