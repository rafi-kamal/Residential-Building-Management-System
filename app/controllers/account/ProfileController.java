package controllers.account;

import java.util.List;

import models.Apartment;
import models.ApartmentBuilding;
import models.UserAccount;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;

public class ProfileController extends Controller {

    /*public static Result index() {
        return ok(views.html.account.profile.render());
    }*/
    
    public static Result index(Long id)
    {
    	UserAccount userAccount;
    	if(id!=-1)
    		userAccount = UserAccount.find.where().eq("apartment_id", id).findUnique();
    	else
    		userAccount = UserAccount.find.where().eq("id", (session().get("userId"))).findUnique();
    	return ok(views.html.account.profile.render(userAccount));
    }
    
    public static Result showAllProfiles()
    {
    	String userId = session().get("userId");
    	UserAccount userAccount = UserAccount.find.byId(Long.parseLong(userId));
    	Logger.debug(String.valueOf(userAccount.apartment.apartmentBuilding.id));
    	ApartmentBuilding building = ApartmentBuilding.find.byId(userAccount.apartment.apartmentBuilding.id);
    	List<Apartment> apartments = Apartment.find.where().eq("apartment_building_id", building.id).findList();
    	return ok(views.html.account.showAllProfiles.render(apartments));
    }
}
