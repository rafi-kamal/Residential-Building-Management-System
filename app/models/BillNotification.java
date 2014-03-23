package models;

import org.joda.time.LocalTime;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class BillNotification extends play.db.ebean.Model {
	
	@ManyToOne
	@Required
	public Bill bill;
	
	@OneToOne
	@Id
	public Notification notification;
}
