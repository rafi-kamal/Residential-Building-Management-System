package controllers.admin;

import java.util.Map;

import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class SignInController extends Controller {

    public static Result index() {
        return ok(views.html.admin.signIn.render());
    }
    
    public static Result signIn() {
    	Map<String, String[]> params = request().body().asFormUrlEncoded();
    	
    	String password = params.get("password")[0];
    	
    	return redirect("/admin");
    }
}
