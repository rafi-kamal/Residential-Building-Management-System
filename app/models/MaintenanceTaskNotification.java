package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;

@Entity
public class MaintenanceTaskNotification extends Notification {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@Required
	public MaintenanceTask maintenanceTask;
	
	public static Finder<Long, MaintenanceTaskNotification> find = 
			new Finder<Long, MaintenanceTaskNotification> (Long.class, MaintenanceTaskNotification.class);
}
