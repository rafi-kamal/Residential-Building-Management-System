package controllers.notifications;

import java.util.ArrayList;
import java.util.List;

import enums.NotificationStatus;
import models.BillNotification;
import models.TaskNotification;
import models.Notification;
import play.mvc.Controller;
import play.mvc.Result;

public class NotificationController extends Controller {

    public static Result index() {
        List<Notification> notifications = new ArrayList<Notification>();
        
        List<BillNotification> billNotifications = BillNotification.find
        		.where()
        		.eq("status", NotificationStatus.Unread)
        		.eq("receiver_id", session("userId"))
        		.findList();
        
        List<TaskNotification> maintenanceNotifications = TaskNotification.find
        		.where()
        		.eq("status", NotificationStatus.Unread)
        		.eq("receiver_id", session("userId"))
        		.findList();
        
        notifications.addAll(billNotifications);
        notifications.addAll(maintenanceNotifications);
        
        return ok(views.html.notification.notification.render(notifications));
    }
}
