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
public class Apartment extends ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;
	
	@Required
	public String apartmentNo;
	
	@Valid
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "internalId")
	public ApartmentBuilding apartmentBuilding;
}