package controllers.admin;

import java.util.List;

import models.UserAccount;
import play.mvc.Controller;
import play.mvc.Result;
import enums.AccountType;

public class UserController extends Controller {

    public static Result index() {
    	List<UserAccount> users = UserAccount.find.all();
        return ok(views.html.admin.users.render(users));
    }
    
    public static Result updateUser(Long userId, String accountType) {
    	return ok(accountType);
    }
}
