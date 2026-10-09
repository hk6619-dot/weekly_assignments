import java.util.*;

public class Week9Practice {

    // Problem 1: Garden Plot Area Report
    public static class Problem1 {
        abstract static class Plot {
            String owner;
            Plot(String owner) { this.owner = owner; }
            abstract double calculateArea();
            abstract String getShape();
        }
        static class Circle extends Plot {
            double radius;
            Circle(String owner, double radius) { super(owner); this.radius = radius; }
            double calculateArea() { return Math.PI * radius * radius; } // Built-in value of Pi[cite: 67]
            String getShape() { return "CIRCLE"; }
        }
        static class Rectangle extends Plot {
            double length, width;
            Rectangle(String owner, double l, double w) { super(owner); this.length = l; this.width = w; }
            double calculateArea() { return length * width; } // Length x Width[cite: 67]
            String getShape() { return "RECTANGLE"; }
        }
        static class Triangle extends Plot {
            double base, height;
            Triangle(String owner, double b, double h) { super(owner); this.base = b; this.height = h; }
            double calculateArea() { return 0.5 * base * height; } // 1/2 x Base x Height[cite: 67]
            String getShape() { return "TRIANGLE"; }
        }
    }

    // Problem 2: Weekly Staff Pay
    public static class Problem2 {
        // A generic staff member with no pay rule must never be created[cite: 68]
        abstract static class Staff {
            String name;
            Staff(String name) { this.name = name; }
            abstract double calculatePay();
        }
        static class FullTime extends Staff {
            double weeklySalary;
            FullTime(String name, double salary) { super(name); this.weeklySalary = salary; }
            double calculatePay() { return weeklySalary; } // Fixed weekly salary[cite: 68]
        }
        static class Hourly extends Staff {
            double hours, rate;
            Hourly(String name, double hours, double rate) { super(name); this.hours = hours; this.rate = rate; }
            double calculatePay() {
                if (hours <= 40) return hours * rate;
                return (40 * rate) + ((hours - 40) * (rate * 1.5)); // 1.5x rate for hours above 40[cite: 68]
            }
        }
        static class Intern extends Staff {
            double stipend;
            Intern(String name, double stipend) { super(name); this.stipend = stipend; }
            double calculatePay() { return stipend; } // Fixed stipend[cite: 68]
        }
    }

    // Problem 3: Library Late Fine Counter
    public static class Problem3 {
        abstract static class LibraryItem {
            String title;
            int daysLate;
            LibraryItem(String title, int daysLate) { this.title = title; this.daysLate = daysLate; }
            abstract double calculateFine();
        }
        static class Book extends LibraryItem {
            Book(String title, int daysLate) { super(title, daysLate); }
            double calculateFine() { return daysLate * 2.0; } // 2 per day late[cite: 69]
        }
        static class DVD extends LibraryItem {
            DVD(String title, int daysLate) { super(title, daysLate); }
            double calculateFine() { return Math.min(50.0, daysLate * 5.0); } // 5 per day, up to maximum of 50[cite: 69]
        }
        static class Magazine extends LibraryItem {
            Magazine(String title, int daysLate) { super(title, daysLate); }
            double calculateFine() { return daysLate * 1.0; } // 1 per day late[cite: 69]
        }
    }

    // Problem 4: Electricity Connection Billing
    public static class Problem4 {
        abstract static class Connection {
            double units;
            Connection(double units) { this.units = units; }
            abstract double calculateBill();
            abstract String getType();
        }
        static class Home extends Connection {
            Home(double units) { super(units); }
            double calculateBill() {
                if (units <= 100) return units * 5.0; // 5 per unit for first 100[cite: 70]
                return (100 * 5.0) + ((units - 100) * 7.0); // 7 per unit after that[cite: 70]
            }
            String getType() { return "HOME"; }
        }
        static class Shop extends Connection {
            Shop(double units) { super(units); }
            double calculateBill() { return (units * 8.0) + 100.0; } // 8 per unit plus fixed 100[cite: 70]
            String getType() { return "SHOP"; }
        }
        static class Factory extends Connection {
            Factory(double units) { super(units); }
            double calculateBill() { return Math.max(1000.0, units * 6.0); } // Minimum bill of 1000[cite: 70]
            String getType() { return "FACTORY"; }
        }
    }

    // Problem 5: Travel Booking with a Common Fee
    public static class Problem5 {
        abstract static class Booking {
            double distance;
            static final double BOOKING_FEE = 50.0; // The fee must be written in one place only[cite: 71]
            
            Booking(double distance) { this.distance = distance; }
            
            abstract double getBaseFare();
            abstract String getMode();
            
            double calculateTotalFare() {
                return getBaseFare() + BOOKING_FEE; // Shared fee applied to base fare[cite: 71]
            }
        }
        static class Bus extends Booking {
            Bus(double d) { super(d); }
            double getBaseFare() { return distance * 2.0; } // 2 per km[cite: 71]
            String getMode() { return "BUS"; }
        }
        static class Train extends Booking {
            Train(double d) { super(d); }
            double getBaseFare() { return distance * 1.5; } // 1.5 per km[cite: 71]
            String getMode() { return "TRAIN"; }
        }
        static class Flight extends Booking {
            Flight(double d) { super(d); }
            double getBaseFare() { return 2500.0 + (distance * 4.0); } // 2500 plus 4 per km[cite: 71]
            String getMode() { return "FLIGHT"; }
        }
    }
}