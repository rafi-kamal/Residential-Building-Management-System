package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import models.enums.AccountType;
import play.data.format.Formats;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class UserAccount extends Model {

	private static final long serialVersionUID = -3698872425235311089L;

	@Id
	@GeneratedValue
	public Long id;
	
	@Required
	@MaxLength(50)
	public String name;
	
	@Required
	@MinLength(5)
	@MaxLength(60)
	@Column(unique=true)
	public String email;
	
	@Required
	@MinLength(6)
	@MaxLength(20)
	public String phone;
	
	@Enumerated(EnumType.STRING)
	public AccountType accountType = AccountType.Resident;
	
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

	@Override
	public String toString() {
		return "UserAccount [name=" + name + ", email=" + email + ", phone="
				+ phone + ", accountType=" + accountType + ", joinDate="
				+ joinDate + "]";
	}
	
}
