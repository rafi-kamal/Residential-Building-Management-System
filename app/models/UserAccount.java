package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import models.enums.AccountType;
import play.data.format.Formats;
import play.data.validation.Constraints.Max;
import play.data.validation.Constraints.Min;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class UserAccount extends Model {

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
	
//	@Required
//	@Enumerated(EnumType.STRING)
//	public VerificationStatus verificationStatus;
	
	@Formats.DateTime(pattern="dd/MM/yyyy")
	public Date joinDate = new Date();
	
	@Valid
	@OneToOne
	public Apartment apartment;
	
	public static Finder<Long, UserAccount> find = 
			new Finder<Long, UserAccount> (Long.class, UserAccount.class);
}
