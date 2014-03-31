package controllers.admin;

import java.util.Map;

import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class UserController extends Controller {

    public static Result index() {
        return ok(views.html.admin.users.render());
    }
}
