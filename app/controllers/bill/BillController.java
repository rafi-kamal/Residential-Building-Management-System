package controllers.bill;

import java.util.List;

import models.*;
import play.data.Form;
import play.mvc.*;
import static scala.collection.JavaConversions.*;

public class BillController extends Controller {
	
	static public Form<Bill> billForm  = Form.form(Bill.class);
	
	public static Result createBill()
	{		
		List<Apartment> apartments = Apartment.find.all();
		return ok(views.html.bill.createBill.render(billForm, asScalaBuffer(apartments)));
	}
	
	public static Result postBill() {
    	Form<Bill> filledBillForm = billForm.bindFromRequest();
    	if (filledBillForm.hasErrors()) {
    		return ok(views.html.bill.postBill.render(filledBillForm));
    		//return ok(filledBillForm.errors().toString());
    	}
    	
    	Bill bill = filledBillForm.get();
    	
    	bill.save();
    	
    	return ok(views.html.bill.postBill.render(filledBillForm));
    	//return ok("Registered " + bill.toString());
    }
	
	public static Result showAllBills() {
		List<Bill> bills = Bill.find.all();
		return ok(views.html.bill.showAllBills.render(bills));
	}
	
	/*public static Result postBill()
	{
		return ok(views.html.bill.postBill.render(billForm.bindFromRequest()));
	}*/
}
