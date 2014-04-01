package controllers.admin;

import play.mvc.Controller;
import play.mvc.Result;

public class AdminPanelController extends Controller {

    public static Result index() {
        return ok(views.html.admin.adminPanel.render());
    }
}
