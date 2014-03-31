package controllers.account;

import java.util.ArrayList;
import java.util.List;

import models.ApartmentBuilding;
import models.RealEstateCompany;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class SelectApartmentController extends Controller {
    
    public static Result index() {
    	List<RealEstateCompany> companies = RealEstateCompany.find.all();
    	return ok(views.html.account.selectApartment.render(companies));
    }
    
    public static Result getApartmentBuildings(Long realEstateId) {
    	List<ApartmentBuilding> apartmentBuildings = ApartmentBuilding.find
    			.where()
    			.eq("real_estate_company_id", realEstateId)
    			.findList();
    	class Container {
    		public Long id;
    		public String name;
			public Container(Long id, String name) {
				this.id = id;
				this.name = name;
			}
    	}
    	List<Container> apartmentBuildingNames = new ArrayList<Container>();
    	for (ApartmentBuilding apartmentBuilding : apartmentBuildings) {
    		apartmentBuildingNames.add(new Container(apartmentBuilding.id, apartmentBuilding.name));
    	}
    	return ok(Json.toJson(apartmentBuildingNames));
    }
    
}
