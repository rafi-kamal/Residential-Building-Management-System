package controllers.account;

import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;

public class UserAccountController extends Controller {
	static Form<UserAccount> userForm = Form.form(UserAccount.class);
}
