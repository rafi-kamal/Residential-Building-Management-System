package models;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import models.enums.NotificationStatus;
import play.data.format.Formats;
import play.data.validation.Constraints.Required;

@MappedSuperclass
public class Notification extends play.db.ebean.Model {

	private static final long serialVersionUID = -3698872425235311089L;

	@Id
	@GeneratedValue
	public Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	public UserAccount receiver;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date issueDate = new Date();
	
	@Required
	@Enumerated(EnumType.STRING)
	public NotificationStatus status;
	
	public static Finder<Long, Notification> find = 
			new Finder<Long, Notification> (Long.class, Notification.class);
	
}
