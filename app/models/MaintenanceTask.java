package models;

import java.util.Date;

import play.data.validation.Constraints.*;
import play.db.ebean.*;

import javax.persistence.*;

@Entity
public class MaintenanceTask extends Model {
	/**
	 * 
	 */
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
	
	@Required
	public Date dateOfIssuing = new Date();

	@Required
	public Date deadline;

	public MaintenanceTask() {
	}	
}
