package controllers.account;

import java.util.Map;

import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class MainController extends Controller {

	static Form<UserAccount> signUpForm = Form.form(UserAccount.class);
	
    public static Result index() {
    	//if (session().containsKey("userId"))
    		//return redirect("/profile/" + session("userId"));
    	//else
    		return ok(views.html.index.render(signUpForm));
    }
    
    public static Result signUp() {
    	Form<UserAccount> filledSignUpForm = signUpForm.bindFromRequest();
    	if (filledSignUpForm.hasErrors()) {
    		return ok(filledSignUpForm.errors().toString());
    	}
    	
    	UserAccount userAccount = filledSignUpForm.get();
    	
    	userAccount.save();
		
    	session().clear();
		session("userId", String.valueOf(userAccount.id));
		session("name", userAccount.name);
		
		return redirect("/selectApartment");
    }
    
    public static Result signIn() {
    	Map<String, String[]> params = request().body().asFormUrlEncoded();
    	
    	String email = params.get("email")[0];
    	String password = params.get("password")[0];
    	
    	try {
    		UserAccount userAccount = UserAccount.find
    				.where()
    					.eq("email", email)
    				.findUnique();
    		
    		if (password.equals(userAccount.password)) {
    			session().clear();
    			session("userId", String.valueOf(userAccount.id));
    			session("name", userAccount.name);
    			return redirect("/profile/" + userAccount.id);
    		}
    		else
    			throw new IllegalStateException();
    	}
    	catch (NullPointerException exception) {
    		return ok("Username doesn't exist");
    	}
    	catch (IllegalStateException exception) {
    		return ok("Invalid password");
    	}
    	
    }
    
    public static Result signOut() {
    	session().clear();
    	return redirect("/");
    }
}
