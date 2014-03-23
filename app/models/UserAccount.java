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
public class UserAccount extends ebean.Model {
	@Id
	@GeneratedValue
	public Long internalId;
	
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
	public LocalDate date;
	
	@Valid
	@Required
	@OneToOne
	@JoinColumn(name="internalId")
	public Apartment apartment;
	
	@Valid
    @OneToMany(cascade=CascadeType.ALL)
    @OrderBy("timestamp")
    @JoinColumn(name="THREAD_ID", referencedColumnName="internal_id")
    public List<Message> items = new ArrayList<Message>();
	
	public UserAccount() {}

	public UserAccount(String firstName, String lastName, String email,
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
