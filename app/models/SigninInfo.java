package models;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class SigninInfo extends Model{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	public String email;
	
	@Required
	@MinLength(4)
	@MaxLength(20)
	public String password;
}
