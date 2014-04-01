package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

@Entity
public class TaskNotification extends Notification {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@Required
	public Task maintenanceTask;
	
	public static Finder<Long, TaskNotification> find = 
			new Finder<Long, TaskNotification> (Long.class, TaskNotification.class);
}
