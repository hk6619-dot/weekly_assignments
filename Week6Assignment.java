// M1. Library Inventory Management
class BookInventory {
    String title;
    String author;
    int copiesAvailable;

    public BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    public void printEntry() {
        System.out.println(title + " by " + author + " " + copiesAvailable + " copies available");
    }
}

// M2. Payroll Salary Management
class PayrollAccount {
    private double basicSalary;
    private double bonus;

    public PayrollAccount(double basicSalary) {
        if (basicSalary < 0) {
            System.out.println("Warning: Basic salary cannot be negative. Starting at 0.");
            this.basicSalary = 0;
        } else {
            this.basicSalary = basicSalary;
        }
        this.bonus = 0;
    }

    public void creditBonus(double amount) {
        if (amount <= 0) {
            System.out.println("Bonus rejected: Amount must be greater than 0."); // Rejects amount <= 0[cite: 9]
        } else {
            this.bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }
    }

    public void deductTax(double percent) {
        if (percent < 0 || percent > 100) {
            System.out.println("Tax deduction rejected: Percentage must be between 0 and 100.");
        } else {
            double deduction = this.basicSalary * (percent / 100);
            this.basicSalary -= deduction;
            System.out.println("Tax deducted: " + percent + "%");
        }
    }

    public double getNetSalary() {
        return this.basicSalary + this.bonus; // Read-only access returning basicSalary + bonus[cite: 9]
    }
}

// M3. Employee Profile Creation
class EmployeeM3 {
    String empId;
    String empName;
    double salary;
    boolean isIntern;

    public EmployeeM3(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false; // Permanent employees join with isIntern set to false[cite: 9]
    }

    public EmployeeM3(String empId, String empName) {
        this(empId, empName, 0.0); // Chains to the three-argument constructor with salary 0[cite: 9]
        this.isIntern = true; // Sets isIntern to true afterwards[cite: 9]
    }

    public void printProfile() {
        System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
    }
}

// M4. Exam Hall Ticket Reference Management
class HallTicket {
    String studentName;
    int seatNumber;

    public HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }
}

// M5. Employee and Company Information Management
class EmployeeM5 {
    String empName;
    double salary;
    
    // Shared by every employee[cite: 9]
    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public EmployeeM5(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++; // Increments once inside the constructor, every time[cite: 9]
    }

    public static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }
}

public class Week6Assignment {
    public static void main(String[] args) {
        // M1 Testing
        BookInventory[] inventory = {
            new BookInventory("Clean Code", "Robert C. Martin", 3),
            new BookInventory("Effective Java", "Joshua Bloch", 5),
            new BookInventory("Refactoring", "Martin Fowler", 0),
            new BookInventory("Design Patterns", "GoF", 2)
        };
        for (BookInventory book : inventory) {
            book.printEntry(); // Print each one in a loop[cite: 9]
        }

        System.out.println("---");

        // M2 Testing
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());

        System.out.println("---");

        // M3 Testing
        EmployeeM3 permanent = new EmployeeM3("E-101", "Divya", 65000);
        EmployeeM3 intern = new EmployeeM3("E-102", "Arjun");
        permanent.printProfile();
        intern.printProfile();

        System.out.println("---");

        // M4 Testing
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copy = priya; // Assign a second variable to point at the same object[cite: 9]
        copy.seatNumber = 45;
        
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        
        HallTicket separate = new HallTicket("Priya", 45); // Third, separate object with identical values[cite: 9]
        System.out.println("separate == priya: " + (separate == priya));

        System.out.println("---");

        // M5 Testing
        new EmployeeM5("Aman", 40000);
        new EmployeeM5("Sita", 45000);
        new EmployeeM5("John", 50000);
        System.out.println("3 Employee objects created");
        EmployeeM5.printCompanyInfo(); // Call through the class name, not through any object[cite: 9]
    }
}