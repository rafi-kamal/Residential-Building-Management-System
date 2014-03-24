package controllers.communications;


import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;
import static scala.collection.JavaConversions.*;
import models.Message;
import models.Thread;
import models.UserAccount;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

//import controllers.Message;
import controllers.routes;

public class ThreadController extends Controller {
	  static public Form<Thread> threadForm  = Form.form(Thread.class);
	  
	  public static Result registerThread() {
		  Map<String, String> m = new HashMap<String, String>();
	      int nextOccurrence =
	        Thread.occurrencesFor(LocalDate.now())+1;
	      m.put("occurrence", ""+nextOccurrence);
	      List<UserAccount> receivers = UserAccount.find.all();
	      return ok(views.html.communications.thread.render(threadForm.bind(m), asScalaBuffer(receivers)));
	  }

	  public static Result loadThread() {
	    Map<String,String[]> queryString = request().queryString();
	    Long threadId = Long.parseLong(queryString.get("threadid")[0]);

	    Thread thread = Thread.find
	                      .where()
	                        .eq("internalId", threadId)
	                        .join("messages")
	                          .fetch("messages.sender")
	                          .fetch("messages.receiver")
	                        .findUnique();

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
	      Form<Thread> boundForm = threadForm.bindFromRequest();
	      if (boundForm.hasErrors()) {
	          return badRequest("Cannot create thread : "
	            + boundForm.errorsAsJson().toString());
	      } else {
	          Thread thread = boundForm.get();
	          UserAccount sender = UserAccount.find.where().eq("email", session("email")).findUnique();
	          thread.sender=sender;
	          thread.save();
	          return redirect( controllers.communications.routes.ThreadController.allThreads() );
	      }
	  }

	  static public Form<Message>  messageForm  = Form.form(Message.class);
	  
	  public static Result talk(Long threadId) {
	    UserAccount sender = UserAccount.find.where().eq("email", session("email")).findUnique();
	    Thread thread = models.Thread.find
	                      .where()
	                        .eq("internalId", threadId)
	                      .join("messages")
	                    .findUnique();
	    
	    Form<Message> boundForm = messageForm.bindFromRequest();
	    Message message = messageForm.bindFromRequest().get();
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
	