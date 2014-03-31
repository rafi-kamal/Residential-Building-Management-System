package controllers.admin;

import models.*;

import java.util.List;
import java.util.Map;

import controllers.account.routes;
import models.RealEstateCompany;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.collection.JavaConversions.*;

public class ApartmentBuildingController extends Controller {
	static Form<ApartmentBuilding> buildingForm = Form.form(ApartmentBuilding.class);
	
	public static Result index(Long id) {
				
		List<ApartmentBuilding> buildings = ApartmentBuilding.find.where().eq("realEstateCompany.id", id).findList();
		
    	
        return ok(views.html.admin.apartmentBuildings.render(
        		asScalaBuffer(buildings), buildingForm, id));
    }
	
	public static Result addBuilding() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		
		String name = params.get("name")[0];
		String address = params.get("address")[0];
		String rsId = params.get("id")[0];
		
		RealEstateCompany company = RealEstateCompany.find.byId(Long.parseLong(rsId));
		
		ApartmentBuilding building = new ApartmentBuilding(name, address, company);
		
		building.save();
		
		List<ApartmentBuilding> buildings = ApartmentBuilding.find.where().eq("realEstateCompany.id", Long.parseLong(rsId)).findList();
		
    	
        return ok(views.html.admin.apartmentBuildings.render(
        		asScalaBuffer(buildings), buildingForm, Long.parseLong(rsId)));
	}

}
