package com.pettyfer.intellijPlugin.backgroundImage;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

/**
 * @author Petty
 */
public class Notice {

    public static void send(String message) {
        Notification notification = new Notification(
                "extras",
                "Notice",
                message,
                NotificationType.INFORMATION);
        Notifications.Bus.notify(notification);
    }

}
