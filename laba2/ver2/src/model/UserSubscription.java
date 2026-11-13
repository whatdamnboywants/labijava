package model;

public class UserSubscription extends SoftwareLicense{

    public UserSubscription(String softwareName, double baseLicensePrice){
        super(softwareName, baseLicensePrice);
    }

    public UserSubscription(String softwareName, double baseLicensePrice, String planName){
        super(softwareName, baseLicensePrice, planName);
    }

    @Override
    public double calculateSoftwarePrice() {
        final double planModifier = switch(getPlanName()){
            case "INDIVIDUAL" -> 1.0;
            case "BUSINESS" -> 1.8;
            case "ENTERPRISE" -> 3.0;
            default -> {
                System.out.println("Неизвестный тариф, применен базовый коэффициент 'INDIVIDUAL' ");
                yield 1.0;
            }
        };

        double finalPrice = getBaseLicensePrice() * planModifier;
        return Math.round(finalPrice * 100.0) / 100.0;
    }


}
