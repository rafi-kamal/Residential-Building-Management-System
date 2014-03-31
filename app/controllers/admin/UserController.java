package controllers.admin;

import java.util.List;

import enums.AccountType;
import models.UserAccount;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public static Result index() {
    	List<UserAccount> users = UserAccount.find.all();
        return ok(views.html.admin.users.render(users));
    }
    
    public static Result updateUser(Long userId, String accountType) {
    	UserAccount user = UserAccount.find.byId(userId);
    	user.accountType = AccountType.valueOf(accountType);
    	user.save();
    	
    	return redirect("/admin/users");
    }
}
