package controllers.admin;

import java.util.List;

import models.RealEstateCompany;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import scala.collection.JavaConversions;

public class RealEstateCompanyController extends Controller {

	static Form<RealEstateCompany> realEstateCompanyForm = Form.form(RealEstateCompany.class);
	
    public static Result index() {
    	List<RealEstateCompany> companies = RealEstateCompany.find
    		.all();
    	
        return ok(views.html.admin.realEstateCompanies.render(
        		JavaConversions.asScalaBuffer(companies), realEstateCompanyForm));
    }
    
    public static Result addCompany() {
    	Form<RealEstateCompany> filledCompanyForm = realEstateCompanyForm.bindFromRequest();
    	if (filledCompanyForm.hasErrors()) {
    		return ok(filledCompanyForm.errors().toString());
    	}
    	
    	RealEstateCompany company = filledCompanyForm.get();
    	
    	company.save();
    	
    	return redirect("admin/realEstateCompanies");
    }
}
