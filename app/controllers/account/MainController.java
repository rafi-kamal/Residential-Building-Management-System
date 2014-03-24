package controllers.account;

import java.util.Map;

import models.SigninInfo;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class MainController extends Controller {

	static Form<UserAccount> signUpForm = Form.form(UserAccount.class);
	static Form<SigninInfo> signInForm = Form.form(SigninInfo.class);
	
    public static Result index() {
        return ok(views.html.index.render(signUpForm, signInForm));
    }
    
    public static Result signUp() {
    	UserAccount userAccount = new UserAccount();
    	userAccount.save();
    	
    	return ok("Registered " + userAccount.toString());
    }
    
    public static Result signIn() {
    	SigninInfo signinInfo = new SigninInfo();
    	
    	Map<String, String[]> params = request().body().asFormUrlEncoded();
    	
    	signinInfo.email = params.get("email")[0];
    	signinInfo.password = params.get("password")[0];
    	
    	return ok("Logged In: " + signinInfo.email);
    }
}
