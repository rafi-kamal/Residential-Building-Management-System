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
	
	public MaintenanceTask() {}

	public MaintenanceTask(Long id, ApartmentBuilding apartmentBuilding,
			String taskType, String description, String status,
			LocalTime deadline) {
		super();
		this.id = id;
		this.apartmentBuilding = apartmentBuilding;
		this.taskType = taskType;
		this.description = description;
		this.status = status;
		this.deadline = deadline;
	}
}
