package models;

import java.util.Date;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

import javax.persistence.*;

@Entity
public class Task extends Model {

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
	public String status = "Queued";
	
	public Date dateOfIssuing = new Date();

	public Date deadline;

	public Task() {
	}	
	
	public Task(ApartmentBuilding apartmentBuilding, String taskType, String description, String status, Date deadline){
		this.apartmentBuilding = apartmentBuilding;
		this.taskType = taskType;
		this.description = description;
		this.status = status;
		this.deadline = deadline;
	}
	
	public static Finder<Long, Task> find = new Finder<Long, Task>(Long.class, Task.class);
}
