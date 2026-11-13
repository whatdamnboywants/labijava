import licenseserver.*;
import model.*;
import service.*;
import service.impl.EmailNotification;
import service.impl.SmsNotification;

public class main {
    public static void main(String[] args){
    System.out.println("Запуск сервиса лицензирования");

    CloudSync cloudSync1 = new CloudSync("Базовый", 150.0);

    NotificationService smsService = new SmsNotification();
    NotificationService emailService = new EmailNotification();

    LicenseServer smsLicense = new LicenseServer (smsService);
    SoftwareLicense userLicense = new UserSubscription("Logic Pro X", 300.0, "BUSINESS");
    double finalPrice1 = userLicense.calculateSoftwarePrice(cloudSync1, 2);
    smsLicense.processPayment(userLicense, "+79533452213", finalPrice1);

    LicenseServer emailLicense = new LicenseServer (emailService);
    SoftwareLicense corporateLicense = new CorporateSubscription("Fl Studio", 300.0, 10);
    double finalPrice2 = corporateLicense.calculateSoftwarePrice();
    emailLicense.processPayment(corporateLicense, "omg@corporateemail.com", finalPrice2);

    LicenseServer.printGlobalStats();
}
}
