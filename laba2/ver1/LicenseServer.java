public class LicenseServer {
    private static int totalOrdersCount = 0;
    private static double totalRevenue = 0;

    public static void processPayment(SoftwareLicense soft, double finalPrice){
        totalOrdersCount++;
        totalRevenue += finalPrice;

        System.out.printf("Успешная оплата: Продукт: %s | Тариф: %s | К оплате: %.2f руб.%n",
                soft.getSoftwareName(), soft.getPlanName(), finalPrice);
        if(soft.getAppliedSync() != null){
            System.out.printf("Добавили облачную синхронизацию, тариф %s, количество подключенных устройств %d%n",
                    soft.getAppliedSync().getSyncPlanName(), soft.getDeviceCount());
        }
    }

    public static void printGlobalStats() {
        System.out.println("\n==================================================");
        System.out.println("   ГЛОБАЛЬНАЯ СТАТИСТИКА СЕРВИСА ЛИЦЕНЗИРОВАНИЯ   ");
        System.out.println("==================================================");
        System.out.println("Всего продуктов продано: " + totalOrdersCount);
        System.out.println("Общая выручка за весь проданный софт: " + Math.round(totalRevenue * 100.0) / 100.0 + " руб.");
        System.out.println("==================================================");
    }

    public static void main(String[] args){
        System.out.println("Запуск сервиса лицензирования");

        CloudSync cloudSync1 = new CloudSync("Базовый", 150.0);

        SoftwareLicense soft1 = new SoftwareLicense("Logic Pro X", 599.0);
        double price1 = soft1.calculateSoftwarePrice();
        processPayment (soft1, price1);

        SoftwareLicense soft2 = new SoftwareLicense("FL Studio 26", -127.0, "business");
        double price2 = soft2.calculateSoftwarePrice(cloudSync1);
        processPayment (soft2, price2);

        SoftwareLicense soft3 = new SoftwareLicense("Ableton Live 10", 699.0, "enterprise");
        double price3 = soft3.calculateSoftwarePrice(cloudSync1, -1);
        processPayment (soft3, price3);

        printGlobalStats();
    }
}
