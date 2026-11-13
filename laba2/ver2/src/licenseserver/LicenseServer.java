package licenseserver;
import model.*;
import service.NotificationService;

public class LicenseServer {
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    private final NotificationService notificationService;

    public LicenseServer(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    public void processPayment(SoftwareLicense soft, String contact, double finalPrice){
        totalOrdersCount++;
        totalRevenue += finalPrice;

        String receipt = String.format("Успешная оплата: Продукт: %s | Тариф: %s | К оплате: %.2f руб.%n",
                soft.getSoftwareName(), soft.getPlanName(), finalPrice);
        if(soft.getAppliedSync() != null){
            receipt += String.format("Добавили облачную синхронизацию, тариф %s, количество подключенных устройств %d%n",
                    soft.getAppliedSync().getSyncPlanName(), soft.getDeviceCount());
        }
        notificationService.sendNotification(receipt, contact);
    }

    public static void printGlobalStats() {
        System.out.println("\n==================================================");
        System.out.println("   ГЛОБАЛЬНАЯ СТАТИСТИКА СЕРВИСА ЛИЦЕНЗИРОВАНИЯ   ");
        System.out.println("==================================================");
        System.out.println("Всего продуктов продано: " + totalOrdersCount);
        System.out.println("Общая выручка за весь проданный софт: " + Math.round(totalRevenue * 100.0) / 100.0 + " руб.");
        System.out.println("==================================================");
    }

}
