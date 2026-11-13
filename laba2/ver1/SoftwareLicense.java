public class SoftwareLicense {
    private final String softwareName;
    private final double baseLicensePrice;
    private final String planName; // INDIVIDUAL * 1.0, BUSINESS * 1,8 , ENTERPRISE * 3.0 >
    private int deviceCount;
    private CloudSync appliedSync = null;

    public SoftwareLicense(String softwareName, double baseLicensePrice){
        this(softwareName, baseLicensePrice, "INDIVIDUAL");
    }

    public SoftwareLicense(String softwareName, double baseLicensePrice, String planName){
        this.softwareName = softwareName;
        if (baseLicensePrice <= 0){
            System.out.println("Цена софта не может быть отрицательной, установлена базовая равная 100 у.е.");
            this.baseLicensePrice = 100.0;
        } else {
            this.baseLicensePrice = baseLicensePrice;
        }
        this.planName = planName.toUpperCase();
    }

    public double calculateSoftwarePrice() {
        final double planModifier = switch(planName){
            case "INDIVIDUAL" -> 1.0;
            case "BUSINESS" -> 1.8;
            case "ENTERPRISE" -> 3.0;
            default -> {
                System.out.println("Неизвестный тариф, применен базовый коэффициент 'INDIVIDUAL' ");
                yield 1.0;
            }
        };

        double finalPrice = baseLicensePrice * planModifier;
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public double calculateSoftwarePrice(CloudSync cloudSync){
        this.appliedSync = cloudSync;
        double priceWithoutSync = calculateSoftwarePrice();
        this.deviceCount = 1;
        double finalPrice = priceWithoutSync + cloudSync.getBaseSyncPrice();
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public double calculateSoftwarePrice(CloudSync cloudSync, int deviceCount){
        if(deviceCount < 1){
            System.out.println("Внимание, вы указали неправильное количество устройств, проверьте! Установлено базовое число 1");
           deviceCount = 1;
        } else{
            this.deviceCount = deviceCount;
        }
        this.appliedSync = cloudSync;
        double priceWithoutSync = calculateSoftwarePrice();

        double finalPrice = priceWithoutSync + cloudSync.getBaseSyncPrice() * deviceCount;
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public String getPlanName() {
        return planName;
    }

    public CloudSync getAppliedSync() {
        return appliedSync;
    }

    public int getDeviceCount(){
        return deviceCount;
    }

}
