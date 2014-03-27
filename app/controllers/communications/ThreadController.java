package controllers.communications;


import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.*;

import com.avaje.ebean.ExpressionList;

import static scala.collection.JavaConversions.*;
import models.Message;
import models.Thread;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import controllers.routes;

public class ThreadController extends Controller {
	  static public Form<Thread> threadForm  = Form.form(Thread.class);
	  static public Form<Message>  messageForm  = Form.form(Message.class);
	  
	  public static Result registerThread() {
		  Map<String, String> m = new HashMap<String, String>();
	      int nextOccurrence =
	        Thread.occurrencesFor(LocalDate.now())+1;
	      m.put("occurrence", ""+nextOccurrence);
	      List<UserAccount> receivers = UserAccount.find.where().ne("id", session("userId")).findList();
	      String message = new String(); 
	      return ok(views.html.communications.thread.render(threadForm.bind(m), asScalaBuffer(receivers), messageForm));
	  }

	  public static Result loadThread() {
	    Map<String,String[]> queryString = request().queryString();
	    Long threadId = Long.parseLong(queryString.get("threadid")[0]);

	    Thread thread = Thread.find
	                      .where()
	                        .eq("internalId", threadId).findUnique();

	    return ok(
	      views.html.communications.viewthread.render(thread, messageForm)
	    );
	  }

	  public static Result allThreads() {
	      return ok(
	          views.html.communications.threads.render(Thread.find.all())
	      );
	  }

	  public static Result createThread() {
		  Map<String, String[]> params = request().body().asFormUrlEncoded();
		  
		  String category = params.get("category")[0];
		  String subject = params.get("subject")[0];
	      String receiverId = params.get("receiver")[0];
	      String body = params.get("body")[0];
	      
	      UserAccount sender = UserAccount.find.byId(Long.parseLong(session("userId")));
	      UserAccount receiver = UserAccount.find.byId(Long.parseLong(receiverId));
          Thread thread = new Thread(category, LocalDate.now(), subject, sender, receiver);
          Message message = new Message(LocalTime.now(), body, sender);
          thread.messages.add(message);
	      thread.save();
	      return redirect( controllers.communications.routes.ThreadController.allThreads() );
	  }

	  
	  public static Result talk(Long threadId) {
		UserAccount sender = UserAccount.find.byId(Long.parseLong(session("userId")));
	    Thread thread = models.Thread.find
	                      .where()
	                        .eq("internalId", threadId)
	                    .findUnique();
	    Form<Message> boundForm = messageForm.bindFromRequest();
	    Message message = boundForm.get();
	    message.sender = sender;
	    thread.messages.add(message);

	    thread.save();

	    return ok(
	        views.html.communications.viewthread.render(thread, messageForm)
	    );
	  }


	  /*public static Form<Image> imageForm = Form.form(Image.class);
	  public static Result receiveImage(Long chatId) {
	    User user = User.find.byId(session("email"));
	    Chat chat = Chat.find
	                      .where()
	                        .eq("internalId", chatId)
	                      .join("items")
	                    .findUnique();

	    //  GET SOME DATA FROM THEN URL FORM ENCODED DATA
	    Form<Image> filledForm = imageForm.bindFromRequest();
	    if(filledForm.hasErrors()) {
	      return badRequest(
	          filledForm.errors().toString()
	      );
	    } else {
	      Http.MultipartFormData body;
	      //  RECOVER THE WHOLE BODY AS MULTIPART
	      body = request().body().asMultipartFormData();

	      //  THE PLAY2 API PROVIDES A WAY TO GET THE FILE
	      //    +> SO EASILY !!!
	      Http.MultipartFormData.FilePart pic = body.getFile("pic");
	      //  CHECK THE IMAGE TYPE IS VALID -- part of the enum
	      if(Image.ImageType.get(pic.getContentType()) == null) {
	        return badRequest(
	          views.html.chatroom.render(chat, itemForm, imageForm)
	        );
	      }

	      Image image = filledForm.get();

	      image.pic = pic.getFile();
	      image.filePath = pic.getFile().getPath();
	      image.user = user;
	      chat.images.add(image);
	      chat.save();

	      return ok(
	          views.html.chatroom.render(chat, itemForm, imageForm)
	      );
	    }*/
}
	