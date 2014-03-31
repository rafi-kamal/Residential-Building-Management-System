package controllers.account;

import java.util.List;

import models.ApartmentBuilding;
import models.RealEstateCompany;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.node.ObjectNode;

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
    	
    	return ok(Json.toJson(apartmentBuildings));
    }
    
}
