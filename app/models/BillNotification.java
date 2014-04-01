package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

@Entity
public class BillNotification extends Notification {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@Required
	public Bill bill;
	
	public static Finder<Long, BillNotification> find = 
			new Finder<Long, BillNotification> (Long.class, BillNotification.class);
	
	@Override
	public String getNotificationMessage() {
		return "You have a new bill " + bill.description
				+ "; issued at " + bill.dateOfIssuing
				+ ". You have to pay it within " + bill.deadline + ".";
	}
}
