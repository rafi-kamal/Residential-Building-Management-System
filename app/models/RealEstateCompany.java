package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class RealEstateCompany extends Model {

	private static final long serialVersionUID = -8057709955601867766L;

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String name;
	
	@Required
	@MinLength(5)
	@MaxLength(60)
	public String email;
	
	@Required
	@MinLength(6)
	@MaxLength(20)
	public String phone;
	
	@Required
	@MaxLength(100)
	public String address;
	
	@Valid
	@OneToMany(fetch=FetchType.LAZY, mappedBy="realEstateCompany", cascade=CascadeType.ALL)
	public List<ApartmentBuilding> apartmentBuildings;
	
	public static Finder<Long, RealEstateCompany> find = 
			new Finder<Long, RealEstateCompany> (Long.class, RealEstateCompany.class);
}
