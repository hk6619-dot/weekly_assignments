import java.util.*;

public class Week9Assignment {

    // Problem 1: Movie Ticket Counter
    public static class Problem1 {
        abstract static class Ticket {
            int count;
            static final double CONVENIENCE_FEE = 20.0; // Written in one place only[cite: 75]
            
            Ticket(int count) { this.count = count; }
            abstract double getSeatPrice();
            abstract String getSeatType();
            
            double calculateTotal() {
                return (getSeatPrice() * count) + (CONVENIENCE_FEE * count); // Adds fee per ticket[cite: 75]
            }
        }
        static class Regular extends Ticket {
            Regular(int count) { super(count); }
            double getSeatPrice() { return 150.0; } // 150 per ticket[cite: 75]
            String getSeatType() { return "REGULAR"; }
        }
        static class Premium extends Ticket {
            Premium(int count) { super(count); }
            double getSeatPrice() { return 250.0; } // 250 per ticket[cite: 75]
            String getSeatType() { return "PREMIUM"; }
        }
        static class Recliner extends Ticket {
            Recliner(int count) { super(count); }
            double getSeatPrice() { return 400.0; } // 400 per ticket[cite: 75]
            String getSeatType() { return "RECLINER"; }
        }
    }

    // Problem 2: Parcel Shipping Desk
    public static class Problem2 {
        interface Insurable {
            double calculateInsurance();
        }
        abstract static class Parcel {
            double weight, declaredValue;
            Parcel(double w, double v) { this.weight = w; this.declaredValue = v; }
            abstract double calculateCharge();
            abstract String getType();
        }
        static class Standard extends Parcel {
            Standard(double w, double v) { super(w, v); }
            double calculateCharge() { return 40 + (10 * weight); } // 40 plus 10 per kg[cite: 76]
            String getType() { return "STANDARD"; }
        }
        static class Express extends Parcel implements Insurable {
            Express(double w, double v) { super(w, v); }
            double calculateCharge() { return 80 + (15 * weight); } // 80 plus 15 per kg[cite: 76]
            public double calculateInsurance() { return declaredValue * 0.02; } // 2% of declared value[cite: 76]
            String getType() { return "EXPRESS"; }
        }
        static class Fragile extends Parcel implements Insurable {
            Fragile(double w, double v) { super(w, v); }
            double calculateCharge() { return (40 + (10 * weight)) + 50; } // Standard plus handling fee of 50[cite: 76]
            public double calculateInsurance() { return declaredValue * 0.02; } // 2% of declared value[cite: 76]
            String getType() { return "FRAGILE"; }
        }
        
        // Example execution loop snippet checking interface capabilities:
        // double insurance = (parcel instanceof Insurable) ? ((Insurable) parcel).calculateInsurance() : 0.0;
    }

    // Problem 3: College Fee Counter
    public static class Problem3 {
        interface BusUser {
            double getTransportFee();
        }
        abstract static class Student {
            String name;
            Student(String name) { this.name = name; }
            abstract double getTuition();
        }
        static class DayScholar extends Student implements BusUser {
            DayScholar(String name) { super(name); }
            double getTuition() { return 40000; } // Tuition 40000[cite: 77]
            public double getTransportFee() { return 12000; } // Bus transport fee[cite: 77]
        }
        static class Hosteller extends Student {
            Hosteller(String name) { super(name); }
            double getTuition() { return 40000 + 60000; } // Tuition plus hostel fee[cite: 77]
        }
        static class Scholar extends Student implements BusUser {
            Scholar(String name) { super(name); }
            double getTuition() { return 20000; } // Half normal tuition[cite: 77]
            public double getTransportFee() { return 12000; } // Bus transport fee[cite: 77]
        }
    }

    // Problem 4: City Cab Fare Meter
    public static class Problem4 {
        interface NightService {}
        
        abstract static class Cab {
            double km;
            boolean isNight;
            Cab(double km, boolean isNight) { this.km = km; this.isNight = isNight; }
            
            abstract double getRate();
            abstract String getType();
            
            double calculateFare() throws IllegalArgumentException {
                if (isNight && !(this instanceof NightService)) {
                    throw new IllegalArgumentException("night service not available"); // Night request in Mini rejected[cite: 77, 78]
                }
                double baseFare = Math.max(100.0, getRate() * km); // Minimum 100[cite: 77]
                if (isNight) {
                    baseFare *= 1.20; // Night trip adds 20%[cite: 78]
                }
                return baseFare;
            }
        }
        static class Mini extends Cab {
            Mini(double km, boolean isNight) { super(km, isNight); }
            double getRate() { return 10.0; } // 10 per km[cite: 77]
            String getType() { return "MINI"; }
        }
        static class Sedan extends Cab implements NightService {
            Sedan(double km, boolean isNight) { super(km, isNight); }
            double getRate() { return 14.0; } // 14 per km[cite: 77]
            String getType() { return "SEDAN"; }
        }
        static class SUV extends Cab implements NightService {
            SUV(double km, boolean isNight) { super(km, isNight); }
            double getRate() { return 18.0; } // 18 per km[cite: 77]
            String getType() { return "SUV"; }
        }
    }

    // Problem 5: Home Appliance Energy Report
    public static class Problem5 {
        interface SaverMode {}
        
        abstract static class Appliance {
            double hours;
            boolean requestSaver;
            
            Appliance(double hours, boolean requestSaver) { this.hours = hours; this.requestSaver = requestSaver; }
            
            abstract double getPowerWatts();
            abstract String getType();
            
            double calculateUnits() throws IllegalArgumentException {
                if (requestSaver && !(this instanceof SaverMode)) {
                    throw new IllegalArgumentException("saver mode not supported"); // Rejects if unsupported[cite: 78]
                }
                double units = (getPowerWatts() * hours) / 1000.0; // Units(kWh) formula[cite: 78]
                if (requestSaver) {
                    units *= 0.75; // Saver mode reduces energy use by 25%[cite: 78]
                }
                return units;
            }
            double calculateCost(double units) {
                return units * 8.0; // Cost = units x 8[cite: 78]
            }
        }
        static class Fridge extends Appliance {
            Fridge(double h, boolean s) { super(h, s); }
            double getPowerWatts() { return 150.0; } // 150 W[cite: 78]
            String getType() { return "FRIDGE"; }
        }
        static class Ac extends Appliance implements SaverMode {
            Ac(double h, boolean s) { super(h, s); }
            double getPowerWatts() { return 1500.0; } // 1500 W[cite: 78]
            String getType() { return "AC"; }
        }
        static class Tv extends Appliance {
            Tv(double h, boolean s) { super(h, s); }
            double getPowerWatts() { return 100.0; } // 100 W[cite: 78]
            String getType() { return "TV"; }
        }
        static class Washer extends Appliance implements SaverMode {
            Washer(double h, boolean s) { super(h, s); }
            double getPowerWatts() { return 500.0; } // 500 W[cite: 78]
            String getType() { return "WASHER"; }
        }
    }
}