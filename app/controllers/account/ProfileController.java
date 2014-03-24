package controllers.account;

import java.util.Map;

import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ProfileController extends Controller {

    public static Result index() {
        return ok(views.html.account.profile.render());
    }
}
