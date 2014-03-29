package controllers.bill;

import java.util.Date;
import java.util.List;
import java.util.Map;

import models.*;
import play.data.Form;
import play.mvc.*;
import static scala.collection.JavaConversions.*;

public class BillController extends Controller {
	
	static public Form<Bill> billForm  = Form.form(Bill.class);
	
	public static Result createBill() {						
		UserAccount user = UserAccount.find.byId(Long.parseLong(session("userId")));
		ApartmentBuilding building = user.apartment.apartmentBuilding;
		List<Apartment> apartments = Apartment.find.where().eq("apartmentBuilding", building).findList();
		return ok(views.html.bill.createBill.render(billForm, asScalaBuffer(apartments)));
		//return ok(views.html.bill.createBill.render(billForm, asScalaBuffer(Apartment.find.all())));
	}
	
	public static Result postBill() {	
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		
		String apt = params.get("apartment")[0];
		String issueDate = params.get("dateOfIssuing")[0];
		String description = params.get("description")[0];
		String status = params.get("status")[0];
		String dline = params.get("deadline")[0];
		String amnt = params.get("amount")[0];
		
		Double amount = Double.parseDouble(amnt);
		
		Date deadline = new Date(dline);
		Date dateOfIssuing = new Date(issueDate);
		
		Apartment apartment = Apartment.find.byId(Long.parseLong(apt));
    	
    	Bill bill = new Bill(apartment, dateOfIssuing, description, status, deadline, amount);
    	bill.save();
    	billForm = billForm.fill(bill);
		
    	return ok(views.html.bill.postBill.render(billForm));
    	//return ok("Registered " + bill.toString());
    }
	
	public static Result showAllBills() {
		List<Bill> bills = Bill.find.all();
		return ok(views.html.bill.showAllBills.render(bills));
	}
}
