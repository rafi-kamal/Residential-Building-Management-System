package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
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
	@OneToMany(fetch=FetchType.LAZY, mappedBy="realEstateCompany", cascade=CascadeType.ALL)
	public List<ApartmentBuilding> apartmentBuildings;
	
	public static Finder<Long, RealEstateCompany> find = 
			new Finder<Long, RealEstateCompany> (Long.class, RealEstateCompany.class);
}
