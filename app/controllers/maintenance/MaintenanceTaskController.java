package controllers.maintenance;

import java.util.Date;
import java.util.Map;

import models.ApartmentBuilding;
import models.MaintenanceTask;
import models.UserAccount;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class MaintenanceTaskController extends Controller {
	
	static public Form<MaintenanceTask> taskForm  = Form.form(MaintenanceTask.class);
	
	public static Result createTask(){		
		return ok(views.html.maintenance.createTask.render(taskForm));
	}
	
	public static Result postTask() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		
		String taskType = params.get("taskType")[0];
		String description = params.get("description")[0];
		String status = params.get("status")[0];
		String dline = params.get("deadline")[0];
		
		Date deadline = new Date(dline);
		UserAccount user =  UserAccount.find.byId(Long.parseLong(session("userId")));
		ApartmentBuilding building = user.apartment.apartmentBuilding;
    	
    	MaintenanceTask maintenanceTask = new MaintenanceTask(building, taskType, description, status, deadline);
    	maintenanceTask.save();
    	taskForm = taskForm.fill(maintenanceTask);
    	return ok(views.html.maintenance.postTask.render(taskForm));
    	//return ok("Registered " + bill.toString());
    }
	
}
