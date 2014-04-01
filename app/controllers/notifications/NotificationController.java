package controllers.notifications;

import java.util.ArrayList;
import java.util.List;

import models.BillNotification;
import models.MessageNotification;
import models.Notification;
import models.TaskNotification;
import play.mvc.Controller;
import play.mvc.Result;
import enums.NotificationStatus;

public class NotificationController extends Controller {

    public static Result index() {
        List<Notification> notifications = new ArrayList<Notification>();
        
        List<BillNotification> billNotifications = BillNotification.find
        		.where()
        		.eq("status", NotificationStatus.Unread)
        		.eq("receiver_id", session("userId"))
        		.findList();
        
        List<MessageNotification> messageNotifications = MessageNotification.find
        		.where()
        		.eq("status", NotificationStatus.Unread)
        		.eq("receiver_id", session("userId"))
        		.findList();
        
        notifications.addAll(billNotifications);
        notifications.addAll(messageNotifications);
        
        return ok(views.html.notification.notification.render(notifications));
    }
}
