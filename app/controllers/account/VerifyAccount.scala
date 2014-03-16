package controllers.account

import play.api.mvc.{Action, Result}
import play.api.mvc.Controller

object VerifyAccount extends Controller {
	def index = Action {
	  Ok(views.html.account.verifyAccount.render())
	}
}