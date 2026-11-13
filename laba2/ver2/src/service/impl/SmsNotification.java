package service.impl;
import service.NotificationService;

public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient != null && (recipient.startsWith("+7") || recipient.startsWith("8")) && (recipient.length() >= 11 && recipient.length() <= 15)) {
            System.out.printf("[SMS] На номер %s отправлено письмо: %s%n", recipient, message);
        } else {
            System.out.println("[SMS] Некорректный номер телефона, смс не отправлено.");
        }
    }
}