// M1. Student Placement Record Management
class PlacementRecord {
    String studentName;
    String company;
    double packageLpa;

    public PlacementRecord(String studentName, String company, double packageLpa) {
        this.studentName = studentName;
        this.company = company;
        this.packageLpa = packageLpa;
    }

    public void printRecord() {
        System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
    }
}

// M2. Hostel Mess Wallet Management
class MessWallet {
    private double balance; // Must never be overwritten directly from outside the class[cite: 10]

    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: Opening balance cannot be negative. Setting to 0.");
            this.balance = 0;
        } else {
            this.balance = openingBalance;
        }
    }

    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: Amount must be greater than 0.");
        } else {
            this.balance += amount;
            System.out.println("Balance after top-up: " + this.balance);
        }
    }

    public void deduct(double amount) {
        if (amount > this.balance) {
            System.out.println("Deduct rejected: insufficient balance"); // Prevents balance from going negative[cite: 10]
        } else {
            this.balance -= amount;
        }
    }

    public double getBalance() {
        return this.balance; // Read-only access[cite: 10]
    }
}

// M3. Course Credit Management
class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    public Course(String code, String title, int credits) {
        this(code, title, credits, 0); // Chains via this(...) with labCredits set to 0[cite: 10]
    }

    public int totalCredits() {
        return credits + labCredits;
    }
}

// M4. Library ID Card Management
class IdCard {
    String name;
    int booksIssued;

    public IdCard(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }
}

// M5. Student and College Information Management
class Student {
    String name;
    int attendance;
    
    // Shared by every student; static fields incremented in constructor[cite: 10]
    static String collegeName = "SRM Institute of Science and Technology"; 
    static int studentCount = 0;

    public Student(String name, int attendance) {
        this.name = name;
        this.attendance = attendance;
        studentCount++; // Increments once inside the constructor[cite: 10]
    }

    // Must not reference any instance field[cite: 10]
    static void printCollegeInfo() {
        System.out.println(collegeName);
        System.out.println("Students created: " + studentCount);
    }
}

public class Week6Practice {
    public static void main(String[] args) {
        // M1 Testing
        PlacementRecord[] records = {
            new PlacementRecord("Ravi", "TCS", 4.5),
            new PlacementRecord("Anitha", "Zoho", 6.2),
            new PlacementRecord("Karthik", "Infosys", 4.0)
        };
        for (PlacementRecord record : records) {
            record.printRecord();
        }

        System.out.println("---");

        // M3 Testing
        Course theory = new Course("21CSC201J", "Data Structures", 4);
        Course lab = new Course("21CSC205L", "DSA Lab", 3, 1);
        System.out.println(theory.code + " total credits: " + theory.totalCredits());
        System.out.println(lab.code + " total credits: " + lab.totalCredits());

        System.out.println("---");

        // M4 Testing
        IdCard ravi = new IdCard("Ravi", 0);
        IdCard duplicate = ravi; // Point to same object[cite: 10]
        duplicate.booksIssued = 3;
        
        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));
        
        IdCard separate = new IdCard("Ravi", 3);
        System.out.println("separate == ravi: " + (separate == ravi));

        System.out.println("---");

        // M5 Testing
        new Student("Alice", 90);
        new Student("Bob", 85);
        Student.printCollegeInfo(); // Call through the class name[cite: 10]
    }
}