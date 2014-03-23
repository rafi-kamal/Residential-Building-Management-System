package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

<<<<<<< HEAD
import play.data.*;
import play.data.validation.Constraints.*;

import javax.validation.Valid;
import javax.persistence.*;
=======
import org.joda.time.LocalTime;

import play.data.validation.Constraints.Required;
>>>>>>> 720acae40e7affb1df72457563dab6daa6819edd

@Entity
public class Bill extends play.db.ebean.Model {

	private static final long serialVersionUID = -8662085771427157296L;

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public Apartment apartment;
	
	public String description;
	
	@Required
	public LocalTime dateOfIssuing;
	
	@Required
	public LocalTime deadline;
	
	@Required
	public String status;
	
	@Required
	public Double amount;
	
<<<<<<< HEAD
	public static Finder<Long, Bill> find = new Finder<Long, Bill> (Long.class, Bill.class);
	
	public Bill() {}

	public Bill(Long id, Apartment apartment, String description,
			LocalTime dateOfIssuing, LocalTime deadline, String status,
			Double amount) {
		super();
		this.id = id;
		this.apartment = apartment;
		this.description = description;
		this.dateOfIssuing = dateOfIssuing;
		this.deadline = deadline;
		this.status = status;
		this.amount = amount;
	}
=======
	@OneToOne
	@Required
	public Long notificationId;
>>>>>>> 720acae40e7affb1df72457563dab6daa6819edd
}
