package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Apartment extends Model {

	private static final long serialVersionUID = -1531581245963642355L;
	
	public Apartment(String apartmentNo, ApartmentBuilding apartmentBuilding) {
		this.apartmentNo = apartmentNo;
		this.apartmentBuilding = apartmentBuilding;
	}

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String apartmentNo;
	
	@OneToOne
	public UserAccount userAccount;

	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	public ApartmentBuilding apartmentBuilding;
	
	public static Finder<Long, Apartment> find = 
			new Finder<Long, Apartment> (Long.class, Apartment.class);

	@Override
	public String toString() {
		return "Apartment [id=" + id + ", apartmentNo=" + apartmentNo
				+ ", apartmentBuilding=" + apartmentBuilding + "]";
	}
	
}