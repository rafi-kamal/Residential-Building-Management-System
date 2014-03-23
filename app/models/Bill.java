package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.joda.time.LocalTime;

import play.data.validation.Constraints.Required;

@Entity
public class Bill extends play.db.ebean.Model {

	private static final long serialVersionUID = -8662085771427157296L;

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
