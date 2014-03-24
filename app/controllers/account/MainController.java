package controllers.account;

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
    	UserAccount userAccount = signUpForm.bindFromRequest().get();
    	userAccount.save();
    	
    	return ok("Registered " + userAccount.toString());
    }
}
