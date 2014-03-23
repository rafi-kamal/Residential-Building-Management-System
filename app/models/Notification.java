package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import models.enums.AccountType;
import models.enums.VerificationStatus;
import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Notification extends Model {

	private static final long serialVersionUID = -3698872425235311089L;

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	public String firstName;
	public String lastName;
	
	@Required
	@Min(5)
	@Max(60)
	public String email;
	
	@Required
	@Min(6)
	@Max(20)
	public String phone;
	
	@Required
	@Enumerated(EnumType.STRING)
	public AccountType accountType;
	
	@Required
	@Enumerated(EnumType.STRING)
	public VerificationStatus verificationStatus;
	
	@Required
	@Column(insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date joinDate;
	
	@Valid
	@Required
	@OneToOne
	public Apartment apartment;
	
	public Notification() {}

	public Notification(String firstName, String lastName, String email,
			String phone, AccountType accountType,
			VerificationStatus verificationStatus) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.accountType = accountType;
		this.verificationStatus = verificationStatus;
	};
	
}
