package controllers.account;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Apartment;
import models.ApartmentBuilding;
import models.RealEstateCompany;
import models.UserAccount;
import play.Logger;
import play.api.db.DB;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class SelectApartmentController extends Controller {
    
    public static Result index() {
    	List<RealEstateCompany> companies = RealEstateCompany.find.all();
    	return ok(views.html.account.selectApartment.render(companies));
    }
    
    static class Container {
		public Long id;
		public String name;
		public Container(Long id, String name) {
			this.id = id;
			this.name = name;
		}
	}
    
    public static Result getApartmentBuildings(Long realEstateId) {
    	List<ApartmentBuilding> apartmentBuildings = ApartmentBuilding.find
    			.where()
    			.eq("real_estate_company_id", realEstateId)
    			.findList();
    
    	List<Container> apartmentBuildingNames = new ArrayList<Container>();
    	for (ApartmentBuilding apartmentBuilding : apartmentBuildings) {
    		apartmentBuildingNames.add(new Container(apartmentBuilding.id, apartmentBuilding.name));
    	}
    	
    	return ok(Json.toJson(apartmentBuildingNames));
    }
    
	public static Result getApartments(Long buildingId) {
		List<Apartment> apartments = Apartment.find
				.where()
				.eq("apartment_building_id", buildingId)
				.findList();
		
		List<Container> apartmentNames = new ArrayList<Container>();
		for (Apartment apartment : apartments) {
			apartmentNames.add(new Container(apartment.id, apartment.apartmentNo));
		}
		
		return ok(Json.toJson(apartmentNames));
	}
    
	public static Result selectApartment() {
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		
		Long apartmentId = Long.parseLong(params.get("apartment")[0]);
		
		UserAccount user = UserAccount.find.byId(Long.parseLong(session("userId")));
		user.apartment = Apartment.find.byId(apartmentId);
		
		Logger.debug(user.toString());
		
		user.save();
		
		user = UserAccount.find.byId(Long.parseLong(session("userId")));
		Logger.debug(user.toString());
		
		return redirect("/profile");
	}
}
