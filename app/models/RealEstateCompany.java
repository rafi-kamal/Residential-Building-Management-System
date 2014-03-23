package models;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import models.enums.AccountType;
import models.enums.VerificationStatus;

import org.joda.time.LocalDate;
import play.db.ebean;

import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;

@Entity
public class RealEstateCompany extends ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;
	
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
	@OneToMany(fetchType=FetchType.LAZY, mappedBy="realEstateCompany")
	public List<ApartmentBuilding> apartmentBuildings;
}
