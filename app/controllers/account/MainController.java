package controllers.account;

import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class MainController extends Controller {
	static Form<UserAccount> userForm = Form.form(UserAccount.class);
	
    public static Result index() {
        return ok(views.html.index.render("Residential Building Management System"));
    }
    
}
