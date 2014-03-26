package controllers.maintenance;

import java.util.List;

import models.*;
import play.data.Form;
import play.mvc.*;
import static scala.collection.JavaConversions.*;

public class MaintenanceTaskController extends Controller {
	
	static public Form<MaintenanceTask> taskForm  = Form.form(MaintenanceTask.class);
	
	public static Result createTask()
	{		
		List<ApartmentBuilding> buildings = ApartmentBuilding.find.all();
		return ok(views.html.maintenance.createTask.render(taskForm, asScalaBuffer(buildings)));
	}
	
	public static Result postTask() {
    	Form<MaintenanceTask> filledTaskForm = taskForm.bindFromRequest();
    	if (filledTaskForm.hasErrors()) {
    		return ok(views.html.maintenance.postTask.render(filledTaskForm));
    		//return ok(filledBillForm.errors().toString());
    	}
    	
    	MaintenanceTask maintenanceTask = filledTaskForm.get();
    	
    	maintenanceTask.save();
    	
    	return ok(views.html.maintenance.postTask.render(filledTaskForm));
    	//return ok("Registered " + bill.toString());
    }
	
	/*public static Result postBill()
	{
		return ok(views.html.bill.postBill.render(billForm.bindFromRequest()));
	}*/
}
