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
	public Long apartmentBuildingId;
	
	@Required
	public Long apartmentNo;
	
	public String description;
	
	@Required
	public LocalTime dateOfIssuing;
	
	@Required
	public LocalTime deadline;
	
	@Required
	public String status;
	
	@Required
	public Double amount;
	
	@OneToOne
	@Required
	public Long notificationId;
}
