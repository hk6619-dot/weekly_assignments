import java.time.LocalDate;
import java.util.*;

public class Week8Assignment {

    // Problem 1: The Canteen Billing Counter
    public static class Problem1 {
        abstract static class Customer {
            double amount;
            Customer(double amount) { this.amount = amount; }
            abstract double calculateFinalAmount();
            abstract String getType();
        }
        static class Student extends Customer {
            Student(double amount) { super(amount); }
            double calculateFinalAmount() { return amount * 0.90; } // 10% discount[cite: 53]
            String getType() { return "STUDENT"; }
        }
        static class Staff extends Customer {
            Staff(double amount) { super(amount); }
            double calculateFinalAmount() { return amount * 0.95; } // 5% discount[cite: 53]
            String getType() { return "STAFF"; }
        }
        static class Guest extends Customer {
            Guest(double amount) { super(amount); }
            double calculateFinalAmount() { return amount + 10; } // Full amount plus 10 service charge[cite: 53]
            String getType() { return "GUEST"; }
        }

        public static void run(Scanner sc) {
            int n = sc.nextInt();
            double grandTotal = 0;
            List<Customer> customers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                double amount = sc.nextDouble();
                if (type.equals("STUDENT")) customers.add(new Student(amount));
                else if (type.equals("STAFF")) customers.add(new Staff(amount));
                else if (type.equals("GUEST")) customers.add(new Guest(amount));
            }
            
            // Polymorphic processing without explicit type checking[cite: 53]
            for (Customer c : customers) {
                double finalAmount = c.calculateFinalAmount();
                System.out.printf("%s: %.2f\n", c.getType(), finalAmount);
                grandTotal += finalAmount;
            }
            System.out.printf("Total: %.2f\n", grandTotal);
        }
    }

    // Problem 2: The Campus Parking Charge Calculator
    public static class Problem2 {
        abstract static class Vehicle {
            int hours;
            Vehicle(int hours) { this.hours = hours; }
            abstract double calculateCharge();
            abstract String getType();
        }
        static class Bike extends Vehicle {
            Bike(int hours) { super(hours); }
            double calculateCharge() { return hours * 10; } // 10 per hour[cite: 54]
            String getType() { return "BIKE"; }
        }
        static class Car extends Vehicle {
            Car(int hours) { super(hours); }
            double calculateCharge() { return 30 + (Math.max(0, hours - 1) * 20); } // 30 first hour, plus 20 for additional[cite: 54]
            String getType() { return "CAR"; }
        }
        static class Truck extends Vehicle {
            Truck(int hours) { super(hours); }
            double calculateCharge() { return Math.max(100, hours * 50); } // 50 per hour, minimum 100[cite: 54]
            String getType() { return "TRUCK"; }
        }
    }

    // Problem 3: The Hostel Electricity Bill
    public static class Problem3 {
        abstract static class Room {
            int units;
            Room(int units) { this.units = units; }
            abstract double calculateBill();
            abstract String getType();
        }
        static class SingleRoom extends Room {
            SingleRoom(int units) { super(units); }
            double calculateBill() { return units * 8; } // 8 per unit[cite: 55]
            String getType() { return "SINGLE"; }
        }
        static class SharedRoom extends Room {
            int occupants;
            SharedRoom(int units, int occupants) { super(units); this.occupants = occupants; }
            double calculateBill() { return (units * 6.0) / occupants; } // 6 per unit, divided equally by occupants[cite: 55]
            String getType() { return "SHARED"; }
        }
        static class AcRoom extends Room {
            AcRoom(int units) { super(units); }
            double calculateBill() { return (units * 10) + 200; } // 10 per unit plus 200 fixed[cite: 55]
            String getType() { return "AC"; }
        }
    }

    // Problem 4: The Festival Bonus Calculator
    public static class Problem4 {
        abstract static class Employee {
            String name;
            double salary;
            Employee(String name, double salary) { this.name = name; this.salary = salary; }
            abstract double calculateBonus();
        }
        static class FullTime extends Employee {
            FullTime(String name, double salary) { super(name, salary); }
            double calculateBonus() { return salary * 0.10; } // 10% of monthly salary[cite: 55]
        }
        static class PartTime extends Employee {
            PartTime(String name, double salary) { super(name, salary); }
            double calculateBonus() { return salary * 0.05; } // 5% of monthly salary[cite: 56]
        }
        static class Intern extends Employee {
            Intern(String name, double salary) { super(name, salary); }
            double calculateBonus() { return 2000; } // Fixed bonus of 2000[cite: 56]
        }
    }

    // Problem 5: The Streaming Plan Renewal Reminder
    public static class Problem5 {
        abstract static class SubscriptionPlan {
            String name;
            LocalDate startDate;
            SubscriptionPlan(String name, LocalDate startDate) { this.name = name; this.startDate = startDate; }
            abstract LocalDate getRenewalDate();
        }
        static class BasicPlan extends SubscriptionPlan {
            BasicPlan(String name, LocalDate startDate) { super(name, startDate); }
            LocalDate getRenewalDate() { return startDate.plusDays(30); } // Valid for 30 days[cite: 56]
        }
        static class StandardPlan extends SubscriptionPlan {
            StandardPlan(String name, LocalDate startDate) { super(name, startDate); }
            LocalDate getRenewalDate() { return startDate.plusDays(90); } // Valid for 90 days[cite: 56]
        }
        static class PremiumPlan extends SubscriptionPlan {
            PremiumPlan(String name, LocalDate startDate) { super(name, startDate); }
            LocalDate getRenewalDate() { return startDate.plusDays(365); } // Valid for 365 days[cite: 56]
        }
        
        public static void run(Scanner sc) {
            int n = sc.nextInt();
            List<SubscriptionPlan> plans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                String name = sc.next();
                LocalDate date = LocalDate.parse(sc.next());
                if (type.equals("BASIC")) plans.add(new BasicPlan(name, date));
                else if (type.equals("STANDARD")) plans.add(new StandardPlan(name, date));
                else if (type.equals("PREMIUM")) plans.add(new PremiumPlan(name, date));
            }
            for (SubscriptionPlan p : plans) {
                System.out.println(p.name + ": " + p.getRenewalDate()); // Process a list without explicit type checking[cite: 57]
            }
        }
    }
}