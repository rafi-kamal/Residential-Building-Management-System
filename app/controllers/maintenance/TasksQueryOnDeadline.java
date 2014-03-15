
package controllers.maintenance;

import play.mvc.Controller;
import play.mvc.Result;

public class TasksQueryOnDeadline extends Controller {
    
    public static Result index() {
        return ok(views.html.maintenance.tasksQueryOnDeadline.render());
    }
    
}
