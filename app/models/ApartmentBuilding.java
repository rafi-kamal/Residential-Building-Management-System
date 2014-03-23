package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class ApartmentBuilding extends Model {
	private static final long serialVersionUID = -888907934350076591L;

	@Id
	@GeneratedValue
	public long id;
	
	@Required
	public String name;
	
	@Required
	@Min(5)
	@Max(60)
	public String email;
	
	@Required
	@Min(6)
	@Max(20)
	public String phone;
	
	@Required
	@Max(100)
	public String address;
	
	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	public RealEstateCompany realEstateCompany;
	
	@Valid
	@OneToMany(fetch=FetchType.LAZY, mappedBy="apartmentBuilding")
	public List<Apartment> apartments;
	
	public static Finder<Long, ApartmentBuilding> find = 
			new Finder<Long, ApartmentBuilding> (Long.class, ApartmentBuilding.class);
	
}
