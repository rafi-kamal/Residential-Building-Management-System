package controllers.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.*;
import play.data.Form;
import play.mvc.*;
import scala.collection.JavaConversions;
import static scala.collection.JavaConversions.*;

public class ApartmentController extends Controller {
	static Form<Apartment> apartmentForm = Form.form(Apartment.class);

	public static Result index(Long id) {
    	List<Apartment> apartments = Apartment.find.where().eq("apartmentBuilding.id", id)
    		.findList();
        return ok(views.html.admin.apartments.render(
        		JavaConversions.asScalaBuffer(apartments), apartmentForm));
	}
	public static Result addApartment() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		
		String apartmentNo = params.get("apartmentNo")[0];
		String bdId = params.get("id")[0];
		
		ApartmentBuilding building = ApartmentBuilding.find.byId(Long.parseLong(bdId));
		
		Apartment apartment = new Apartment(apartmentNo);
		
		List<Apartment> apartments = Apartment.find.where().eq("apartmentBuilding.id", bdId).findList();
		
    	
        return ok(views.html.admin.apartmentBuildings.render(
        		JavaConversions.asScalaBuffer(apartments), apartmentForm));
	}
}
