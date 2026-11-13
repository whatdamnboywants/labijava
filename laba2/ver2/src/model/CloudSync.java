package model;

public class CloudSync {
    private final String syncPlanName;

    private double baseSyncPrice;

    public CloudSync(String syncPlanName, double baseSyncPrice){
        this.syncPlanName = syncPlanName;
        setBaseSyncPrice(baseSyncPrice);
    }

    public String getSyncPlanName(){
        return syncPlanName;
    }

    public double getBaseSyncPrice() {
        return baseSyncPrice;
    }

    public double setBaseSyncPrice(double baseSyncPrice){
        if (baseSyncPrice < 0){
            System.out.println("Базовая стоимость облачной синхронизации меньше 0");
            this.baseSyncPrice = 0.0;
        } else {
            this.baseSyncPrice = baseSyncPrice;
        }
        return baseSyncPrice;
    }

}
