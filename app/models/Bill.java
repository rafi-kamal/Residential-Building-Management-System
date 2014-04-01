package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.format.Formats;
import play.db.ebean.*;

import javax.persistence.*;

import play.data.validation.Constraints.Required;

@Entity
public class Bill extends Model {

	private static final long serialVersionUID = -8662085771427157296L;

	@Id
	@GeneratedValue
	public Long id;

	@ManyToOne
	public Apartment apartment;

	@Required
	public String description;

	@Formats.DateTime(pattern = "YYYY-MM-DD")
	public Date dateOfIssuing;

	@Formats.DateTime(pattern = "YYY-MM-DD")
	public Date deadline;

	@Required
	public String status = "Unpaid";

	@Required
	public Double amount;

	public static Finder<Long, Bill> find = new Finder<Long, Bill>(Long.class,
			Bill.class);

	public Bill() {
	}

	public Bill(Apartment apartment, Date dateOfIssuing, String description,
			String status, Date deadline, Double amount) {
			this.apartment = apartment;
			this.dateOfIssuing = dateOfIssuing;
			this.description =  description;
			this.status = status;
			this.deadline = deadline;
			this.amount = amount;
	}

	@Override
	public String toString() {
		return "Bill\n" + description + ", Date Issued : " + dateOfIssuing
				+ ", Deadline = " + deadline + ", status = " + status
				+ ", Amount = " + amount;
	}
}
