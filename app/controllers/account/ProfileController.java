package controllers.account;

import play.mvc.Controller;
import play.mvc.Result;

public class ProfileController extends Controller {

    public static Result index() {
        return ok(views.html.account.profile.render());
    }
}
