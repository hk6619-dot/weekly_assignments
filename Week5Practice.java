import java.util.Arrays;

public class Week5Practice {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // 1. Hackathon Score Curve Booster
    public static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] += bonus; // Modifies the caller's array directly[cite: 8]
        }
    }

    // 2. Duplicate Team Name Finder
    public static String findDuplicateTeam(String[] teamNames) {
        for (int i = 0; i < teamNames.length; i++) {
            for (int j = i + 1; j < teamNames.length; j++) {
                if (teamNames[i].equals(teamNames[j])) {
                    return "Duplicate Found: " + teamNames[i]; // Early exit on first duplicate[cite: 8]
                }
            }
        }
        return "No Duplicates Found";
    }

    // 3. Top-3 Podium Finder
    public static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int score : scores) { // Single pass tracking[cite: 8]
            if (score > first) {
                third = second;
                second = first;
                first = score;
            } else if (score > second) {
                third = second;
                second = score;
            } else if (score > third) {
                third = score;
            }
        }
        return new int[]{first, second, third};
    }

    // 4. Hackathon Seating Grid Optimizer
    private static double rowAverage(int[] row) {
        if (row.length == 0) return 0.0;
        int sum = 0;
        for (int score : row) {
            sum += score;
        }
        return (double) sum / row.length;
    }

    public static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < seatingScores.length; i++) {
            double avg = rowAverage(seatingScores[i]); // Do not recompute inline[cite: 8]
            if (avg >= threshold) {
                result.append("Row ").append(i).append(": Buzzing Zone");
            } else {
                result.append("Row ").append(i).append(": Quiet Zone");
            }
            if (i < seatingScores.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }
}

// 5. Placement Drive Shortlisting & Ranking Engine
class Candidate implements Comparable<Candidate> {
    String name;
    double cgpa;
    int codingScore;
    double compositeScore;

    public Candidate(String name, double cgpa, int codingScore) {
        this.name = name;
        this.cgpa = cgpa;
        this.codingScore = codingScore;
        this.compositeScore = (cgpa * 10) + (codingScore / 2.0); // Derived from sample outputs[cite: 8]
    }

    // CGPA-only filter (e.g., above 7.5)[cite: 8]
    static boolean isEligible(double cgpa) {
        return cgpa >= 7.5;
    }

    // Combined borderline filter[cite: 8]
    static boolean isEligible(double cgpa, int codingScore) {
        return cgpa >= 6.5 && codingScore >= 60;
    }

    @Override
    public int compareTo(Candidate other) {
        return Double.compare(other.compositeScore, this.compositeScore); // Descending order[cite: 8]
    }

    static String shortlistAndRank(Candidate[] candidates) {
        // Count eligible candidates first
        int count = 0;
        for (Candidate c : candidates) {
            if (isEligible(c.cgpa) || isEligible(c.cgpa, c.codingScore)) {
                count++;
            }
        }

        Candidate[] shortlisted = new Candidate[count];
        int index = 0;
        for (Candidate c : candidates) {
            if (isEligible(c.cgpa) || isEligible(c.cgpa, c.codingScore)) {
                shortlisted[index++] = c;
            }
        }

        Arrays.sort(shortlisted); // Arrays.sort does the entire ranking[cite: 8]

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < shortlisted.length; i++) {
            result.append((i + 1)).append(". ").append(shortlisted[i].name)
                  .append(" (").append(shortlisted[i].compositeScore).append(")");
            if (i < shortlisted.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }
}