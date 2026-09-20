// 1. The Piggy Bank
class PiggyBank {
    private final String id; // Locked in place forever, right from the constructor[cite: 12]
    private double savings;

    public PiggyBank(String id) {
        this.id = id;
        this.savings = 0; // A new piggy bank starts at 0 savings[cite: 12]
    }

    public void deposit(double amount) {
        if (amount > 0) {
            savings += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > savings) {
            System.out.println("Withdrawal rejected: Insufficient savings."); // Larger withdrawal must be rejected[cite: 12]
        } else if (amount > 0) {
            savings -= amount;
        }
    }

    public double getSavings() { // Read-only access, no setter[cite: 12]
        return savings; 
    }
}

// 2. The Quiz Scorecard
class Scorecard {
    private final boolean[] results;
    private int count;

    public Scorecard(int maxQuestions) {
        this.results = new boolean[maxQuestions]; // Fixed when the scorecard is created[cite: 12]
        this.count = 0;
    }

    public void recordAnswer(boolean isCorrect) {
        if (count < results.length) {
            results[count++] = isCorrect;
        }
    }

    public int getScore() {
        int score = 0;
        for (int i = 0; i < count; i++) {
            if (results[i]) {
                score++;
            }
        }
        return score; // Reveals only the final score, never the array itself[cite: 12]
    }
}

// 3. The Nickname Tag
class NameTag {
    private final String firstName; // Final fields, unchangeable after creation[cite: 12]
    private final String lastInitial;

    public NameTag(String fullName) {
        String[] parts = fullName.split(" "); // Split text apart wherever a space appears[cite: 12]
        this.firstName = parts[0];
        this.lastInitial = parts[1].substring(0, 1) + "."; // First name plus the last name's initial[cite: 12]
    }

    public String getNickname() {
        return firstName + " " + lastInitial;
    }
}

// 4. The Locker Code
class Locker {
    private final int lockerNumber;
    private String combinationCode; // Private, with no getter at all[cite: 12]

    public Locker(int lockerNumber, String initialCode) {
        this.lockerNumber = lockerNumber;
        this.combinationCode = initialCode;
    }

    public void changeCode(String currentCode, String newCode) {
        if (this.combinationCode.equals(currentCode)) { // Check the old code first[cite: 12]
            this.combinationCode = newCode;
            System.out.println("success");
        } else {
            System.out.println("rejected, code is still unchanged"); // Leaves combination unchanged on fail[cite: 12]
        }
    }
}

// 5. The Attendance Sheet
class AttendanceSheet {
    private final String[] presentStudents;
    private int count;

    public AttendanceSheet(int maxClassSize) {
        this.presentStudents = new String[maxClassSize];
        this.count = 0;
    }

    public void markPresent(String name) {
        if (!isPresent(name) && count < presentStudents.length) { // Check whether it's already in the array[cite: 12]
            presentStudents[count++] = name;
        }
    }

    public boolean isPresent(String name) {
        for (int i = 0; i < count; i++) {
            if (presentStudents[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int getPresentCount() {
        return count; // Count and yes/no lookup only, no full list exposed[cite: 12]
    }
}

public class Week7Practice {
    public static void main(String[] args) {
        // Test calls can be placed here
    }
}