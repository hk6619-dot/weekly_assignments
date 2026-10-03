import java.time.LocalDate;
import java.util.*;

public class Week8Practice {

    // Problem 1: Payment System Fee Calculation
    public static class Problem1 {
        abstract static class Payment {
            double amount;
            Payment(double amount) { this.amount = amount; }
            abstract double calculateFinalAmount();
            abstract String getType();
        }
        static class CardPayment extends Payment {
            CardPayment(double amount) { super(amount); }
            double calculateFinalAmount() { return amount * 1.02; } // 2% fee[cite: 58]
            String getType() { return "CARD"; }
        }
        static class WalletPayment extends Payment {
            WalletPayment(double amount) { super(amount); }
            double calculateFinalAmount() { return amount * 1.01; } // 1% fee[cite: 58]
            String getType() { return "WALLET"; }
        }
        static class BankTransferPayment extends Payment {
            BankTransferPayment(double amount) { super(amount); }
            double calculateFinalAmount() { return amount; } // No fee[cite: 58]
            String getType() { return "BANKTRANSFER"; }
        }

        public static void run(Scanner sc) {
            int n = sc.nextInt();
            double grandTotal = 0;
            List<Payment> payments = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                double amount = sc.nextDouble();
                if (type.equals("CARD")) payments.add(new CardPayment(amount));
                else if (type.equals("WALLET")) payments.add(new WalletPayment(amount));
                else if (type.equals("BANKTRANSFER")) payments.add(new BankTransferPayment(amount));
            }
            for (Payment p : payments) {
                double finalAmount = p.calculateFinalAmount();
                System.out.printf("%s: %.2f\n", p.getType(), finalAmount);
                grandTotal += finalAmount;
            }
            System.out.printf("Total: %.2f\n", grandTotal);
        }
    }

    // Problem 2: Library Item Due Date Calculator
    public static class Problem2 {
        abstract static class LibraryItem {
            String title;
            LibraryItem(String title) { this.title = title; }
            abstract LocalDate calculateDueDate(LocalDate currentDate);
        }
        static class Book extends LibraryItem {
            Book(String title) { super(title); }
            LocalDate calculateDueDate(LocalDate currentDate) { return currentDate.plusDays(14); } // 14 days[cite: 59]
        }
        static class DVD extends LibraryItem {
            DVD(String title) { super(title); }
            LocalDate calculateDueDate(LocalDate currentDate) { return currentDate.plusDays(7); } // 7 days[cite: 59]
        }
        static class Magazine extends LibraryItem {
            Magazine(String title) { super(title); }
            LocalDate calculateDueDate(LocalDate currentDate) { return currentDate.plusDays(3); } // 3 days[cite: 59]
        }

        public static void run(Scanner sc) {
            int n = sc.nextInt();
            sc.nextLine();
            LocalDate currentDate = LocalDate.of(2023, 10, 26); // Assumed current date[cite: 59]
            List<LibraryItem> items = new ArrayList<>();
            
            for (int i = 0; i < n; i++) {
                String type = sc.next();
                String title = sc.nextLine().trim().replace("\"", "");
                if (type.equals("BOOK")) items.add(new Book(title));
                else if (type.equals("DVD")) items.add(new DVD(title));
                else if (type.equals("MAGAZINE")) items.add(new Magazine(title));
            }
            for (LibraryItem item : items) {
                System.out.println(item.title + ": " + item.calculateDueDate(currentDate));
            }
        }
    }

    // Problem 3: Delivery Fee Calculator
    public static class Problem3 {
        abstract static class Delivery {
            double weight, distance;
            Delivery(double weight, double distance) { this.weight = weight; this.distance = distance; }
            abstract double calculateFee();
            abstract String getType();
        }
        static class StandardDelivery extends Delivery {
            StandardDelivery(double w, double d) { super(w, d); }
            double calculateFee() { return 5 + (0.50 * weight) + (0.10 * distance); } // Base 5, 0.5/kg, 0.1/km[cite: 60]
            String getType() { return "STANDARD"; }
        }
        static class ExpressDelivery extends Delivery {
            ExpressDelivery(double w, double d) { super(w, d); }
            double calculateFee() { return 15 + (1.00 * weight) + (0.20 * distance); } // Base 15, 1.0/kg, 0.2/km[cite: 60]
            String getType() { return "EXPRESS"; }
        }
        static class InternationalDelivery extends Delivery {
            double customsFee;
            InternationalDelivery(double w, double d, double customsFee) { super(w, d); this.customsFee = customsFee; }
            double calculateFee() { return 25 + (2.00 * weight) + (0.50 * distance) + customsFee; } // Base 25, 2.0/kg, 0.5/km, plus customs[cite: 60]
            String getType() { return "INTERNATIONAL"; }
        }
        // Parsing omitted for brevity; follows same polymorphic pattern as above.
    }

    // Problem 4: Examination Question Grader
    public static class Problem4 {
        abstract static class Question {
            String correctAnswer, studentAnswer;
            double points;
            Question(String c, String s, double p) { this.correctAnswer = c; this.studentAnswer = s; this.points = p; }
            abstract double evaluate();
            abstract String getType();
        }
        static class MCQ extends Question {
            MCQ(String c, String s, double p) { super(c, s, p); }
            double evaluate() { return studentAnswer.equals(correctAnswer) ? points : 0; } // Full points if exact match[cite: 61]
            String getType() { return "MCQ"; }
        }
        static class Essay extends Question {
            Essay(String c, String s, double p) { super(c, s, p); }
            double evaluate() {
                String[] keywords = correctAnswer.split(",");
                int matchCount = 0;
                String ans = studentAnswer.toLowerCase();
                for (String k : keywords) {
                    if (ans.contains(k.trim().toLowerCase())) matchCount++;
                }
                if (matchCount >= 2) return points * 0.75; // Award 75% if 2+ keywords match[cite: 61]
                if (matchCount == 1) return points * 0.50; // Award 50% if 1 keyword matches[cite: 61]
                return 0;
            }
            String getType() { return "ESSAY"; }
        }
    }

    // Problem 5: Public Transport Fare Calculator
    public static class Problem5 {
        abstract static class Transport {
            double distance;
            Transport(double distance) { this.distance = distance; }
            abstract double calculateFare();
            abstract String getType();
        }
        static class Bus extends Transport {
            Bus(double d) { super(d); }
            double calculateFare() { return Math.min(10.0, 2 + 0.10 * distance); } // Base 2, 0.1/km, max 10[cite: 62]
            String getType() { return "BUS"; }
        }
        static class Metro extends Transport {
            double peakFactor;
            Metro(double d, double peak) { super(d); this.peakFactor = peak; }
            double calculateFare() { return (1.50 + 0.20 * distance) * peakFactor; } // Base 1.5, 0.2/km, multiplied by peak[cite: 62]
            String getType() { return "METRO"; }
        }
    }
}