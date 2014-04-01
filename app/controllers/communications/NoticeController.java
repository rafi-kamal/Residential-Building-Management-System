package controllers.communications;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import static scala.collection.JavaConversions.*;
import models.Message;
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
		Map<String, String[]> params = request().body().asFormUrlEncoded();
		  
		String category = params.get("category")[0];
		String subject = params.get("subject")[0];
	    String description = params.get("description")[0];
	    String validity = params.get("validUntil")[0];
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    Date validUntil = null;
	    
		try {
			validUntil = dateFormat.parse(validity);
			//Logger.debug(deadline.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    UserAccount publishedBy = UserAccount.find.where().eq("id", Long.parseLong(session("userId"))).findUnique();
	    Notice notice = new Notice(category, subject, LocalDate.now(), validUntil, description, publishedBy);
	    notice.save();
	    return ok(
	  	      views.html.communications.viewnotice.render(notice) );
	}
	
	public static Result loadNotice() {
	    Map<String,String[]> queryString = request().queryString();
	    Long noticeId = Long.parseLong(queryString.get("internalId")[0]);
	    Notice notice = Notice.find
	                      .where()
	                        .eq("internalId", noticeId)
	                        .findUnique();
	    notice.viewcount++;

	    return ok(
	      views.html.communications.viewnotice.render(notice) );
	  }
	
	public static Result noticeBoard() {
		Date current = new Date();
		List<Notice> noticeList = Notice.find.where().ge("validUntil", current).findList();
		return ok(views.html.communications.noticeboard.render(noticeList));
	}
	
	
}
