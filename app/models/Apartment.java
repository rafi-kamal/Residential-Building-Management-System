package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Apartment extends Model {

	private static final long serialVersionUID = -1531581245963642355L;

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String apartmentNo;
	
	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	public ApartmentBuilding apartmentBuilding;
}