package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class ShowTasks extends Controller {
    
    public static Result index() {
        return ok(views.html.showTasks.render());
    }
    
}
