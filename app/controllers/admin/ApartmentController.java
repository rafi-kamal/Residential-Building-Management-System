package controllers.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.*;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import static scala.collection.JavaConversions.*;

public class ApartmentController extends Controller {
	static Form<Apartment> apartmentForm = Form.form(Apartment.class);

	public static Result index(Long id) {
    	List<Apartment> apartments = Apartment.find.where().eq("apartmentBuilding.id", id)
    		.findList();
        return ok(views.html.admin.apartments.render(
        		asScalaBuffer(apartments), apartmentForm, id));
	}
	public static Result addApartment() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		
		String apartmentNo = params.get("apartmentNo")[0];
		String bdId = params.get("id")[0];
		
		ApartmentBuilding building = ApartmentBuilding.find.byId(Long.parseLong(bdId));
		
		Apartment apartment = new Apartment(apartmentNo);
		Logger.debug(bdId);
		
		apartment.save();
		
		List<Apartment> apartments = Apartment.find.where().eq("apartmentBuilding.id", Long.parseLong(bdId)).findList();
		
    	
        return ok(views.html.admin.apartments.render(
        		asScalaBuffer(apartments), apartmentForm, Long.parseLong(bdId)));
	}
}
