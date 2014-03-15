
package controllers.maintenance;

import play.mvc.Controller;
import play.mvc.Result;

public class TasksQueryOnMultiple extends Controller {
    
    public static Result index() {
        return ok(views.html.maintenance.tasksQueryOnMultiple.render());
    }
    
}
