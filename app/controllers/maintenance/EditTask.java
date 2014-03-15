package controllers.maintenance;

import play.mvc.Controller;
import play.mvc.Result;

public class EditTask extends Controller {
    
    public static Result index() {
        return ok(views.html.maintenance.editTask.render());
    }
    
}
