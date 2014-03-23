package models;

import org.joda.time.LocalTime;

import play.data.*;
import play.data.validation.Constraints.*;
import javax.validation.Valid;

import javax.persistence.*;

@Entity
public class MaintenanceTask extends play.db.ebean.Model {
	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public ApartmentBuilding apartmentBuilding;
	
	@Required
	public String taskType;
	
	public String description;
	
	@Required
	public String status;
	
	@Required
	public LocalTime deadline;
}
