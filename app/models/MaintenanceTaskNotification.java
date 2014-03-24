package models;

import org.joda.time.LocalTime;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class MaintenanceTaskNotification extends play.db.ebean.Model {
	
	@ManyToOne
	@Required
	public MaintenanceTask maintenanceTask;
	
	
	public Notification notification;
}
