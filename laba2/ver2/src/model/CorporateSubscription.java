package model;

public class CorporateSubscription extends SoftwareLicense{

    private final int activeLicensesCount;

    public CorporateSubscription(String softwareName, double baseLicensePrice, int activeLicensesCount){
        super(softwareName, baseLicensePrice);
        if(activeLicensesCount < 1){
            System.out.println("Введено некорректное значение количества лицензий, автоматически установлено значение 1");
            this.activeLicensesCount = 1;
        } else {
            this.activeLicensesCount = activeLicensesCount;
        };
    }

    public CorporateSubscription(String softwareName, double baseLicensePrice, String planName, int activeLicensesCount){
        super(softwareName, baseLicensePrice, planName);
        if(activeLicensesCount < 1){
            System.out.println("Введено некорректное значение количества лицензий, автоматически установлено значение 1");
            this.activeLicensesCount = 1;
        } else {
            this.activeLicensesCount = activeLicensesCount;
        };
    }

    @Override
    public double calculateSoftwarePrice(){
        final double planModifier = switch(getPlanName()){
            case "INDIVIDUAL" -> 1.0;
            case "BUSINESS" -> 1.8;
            case "ENTERPRISE" -> 3.0;
            default -> {
                System.out.println("Неизвестный тариф, применен базовый коэффициент 'INDIVIDUAL' ");
                yield 1.0;
            }
        };

        double finalPrice = getBaseLicensePrice() * planModifier * activeLicensesCount;
        return Math.round(finalPrice * 100.0) / 100.0;
    }

    @Override
    public double calculateSoftwarePrice(CloudSync cloudSync){
        System.out.println("Облачная синхронизация недоступна для корпоративных клиентов, расчет ведем без ее учета");
        return calculateSoftwarePrice();
    }

    @Override
    public double calculateSoftwarePrice(CloudSync cloudSync, int deviceCount){
        System.out.println("Облачная синхронизация недоступна для корпоративных клиентов, расчет ведем без ее учета");
        return calculateSoftwarePrice();
    }

}
