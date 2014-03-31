package controllers.admin;

import models.*;

import java.util.List;
import java.util.Map;

import models.RealEstateCompany;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.collection.JavaConversions;

public class ApartmentBuildingController extends Controller{
	static Form<ApartmentBuilding> buildingForm = Form.form(ApartmentBuilding.class);
	
	public static Result index() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		String id = params.get("id")[0];
				
		List<ApartmentBuilding> buldings = ApartmentBuilding.find.where().eq("realEstateCompany.id", id).findList();
		
    	
        return ok(views.html.admin.apartmentBuildings.render(
        		JavaConversions.asScalaBuffer(buildings), buildingForm));
    }
	
	public Static Result addBuilding() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
	}

}
