
package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class TasksQueryOnMultiple extends Controller {
    
    public static Result index() {
        return ok(views.html.tasksQueryOnMultiple.render());
    }
    
}
