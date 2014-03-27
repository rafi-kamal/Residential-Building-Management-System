package models;

import java.util.Date;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

import javax.persistence.*;

@Entity
public class MaintenanceTask extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	public Long id;

	@ManyToOne
	public ApartmentBuilding apartmentBuilding;

	@Required
	public String taskType;

	public String description;

	@Required
	public String status;
	
	public Date dateOfIssuing = new Date();

	public Date deadline;

	public MaintenanceTask() {
	}	
	
	public MaintenanceTask(ApartmentBuilding apartmentBuilding, String taskType, String description, String status, Date deadline){
		this.apartmentBuilding = apartmentBuilding;
		this.taskType = taskType;
		this.description = description;
		this.status = status;
		this.deadline = deadline;
	}
}
