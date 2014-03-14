
package controllers.maintenance;

import play.mvc.Controller;
import play.mvc.Result;

public class TasksQueryOnType extends Controller {
    
    public static Result index() {
        return ok(views.html.tasksQueryOnType.render());
    }
    
}
