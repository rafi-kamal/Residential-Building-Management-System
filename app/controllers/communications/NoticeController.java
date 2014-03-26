package controllers.communications;


import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import static scala.collection.JavaConversions.*;
import models.Notice;
import models.Thread;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.routes;

public class NoticeController extends Controller{
	static public Form<Notice> noticeForm  = Form.form(Notice.class);
	
	public static Result newNotice() {
	      return ok(views.html.communications.newnotice.render(noticeForm));
	}
	
	public static Result createNotice() {
		Form<Notice> boundForm = noticeForm.bindFromRequest();
	      if (boundForm.hasErrors()) {
	          return badRequest("Cannot create notcie : "
	            + boundForm.errorsAsJson().toString());
	      } else {
	          Notice notice = boundForm.get();
	          UserAccount publishedBy  = UserAccount.find.where().eq("id", session("userId")).findUnique();
	          notice.publishedBy = publishedBy;
	          notice.save();
	          return redirect( controllers.communications.routes.NoticeController.noticeBoard() );
	      }
	}
	
	public static Result loadNotice() {
	    Map<String,String[]> queryString = request().queryString();
	    Long noticeId = Long.parseLong(queryString.get("noticeId")[0]);
	    Notice notice = Notice.find
	                      .where()
	                        .eq("internalId", noticeId)
	                        .findUnique();

	    return ok(
	      views.html.communications.viewnotice.render(notice) );
	  }
	
	public static Result noticeBoard() {
		List<Notice> noticeList = Notice.find.all();
		return ok(views.html.communications.noticeboard.render(noticeList));
	}
	
	
}
