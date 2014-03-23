package models;

import org.joda.time.LocalTime;

import play.data.*;
import play.data.validation.Constraints.*;

import javax.validation.Valid;
import javax.persistence.*;

@Entity
public class Bill extends play.db.ebean.Model {
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
}
