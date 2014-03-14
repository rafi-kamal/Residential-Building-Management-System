package controllers.account

import play.api.mvc.{Action, Result}
import play.api.mvc.Controller

object SelectApartment extends Controller {
	def index = Action {
	  Ok(views.html.account.apartmentSelect.render())
	}
}