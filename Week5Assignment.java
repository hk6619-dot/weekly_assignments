import java.util.Arrays;

public class Week5Assignment {

    public static void main(String[] args) {
        // Test calls can be placed here
    }

    // 1. Fantasy Team Score Multiplier
    public static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        // Only the two specific positions change; no loop needed[cite: 7]
        playerScores[captainIndex] *= 2.0; 
        playerScores[viceCaptainIndex] *= 1.5;
    }

    // 2. Duplicate Player Pick Checker
    public static String findDuplicatePick(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            for (int j = i + 1; j < playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    return "Duplicate Found: " + playerNames[i]; // Report first duplicate found[cite: 7]
                }
            }
        }
        return "No Duplicates Found";
    }

    // 3. Top Performer Tracker
    public static String findMinMaxSpread(int[] scores) {
        if (scores.length == 0) return "";
        
        int min = scores[0];
        int max = scores[0];

        // Do not sort; find min and max in a single pass[cite: 7]
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        int spread = max - min;
        return "Min: " + min + " Max: " + max + " Spread: " + spread; // Report together[cite: 7]
    }

    // 4. Match Day Grid Analyzer
    private static double rowAverage(int[] row) {
        if (row.length == 0) return 0.0;
        int sum = 0;
        for (int runs : row) {
            sum += runs;
        }
        return (double) sum / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            double avg = rowAverage(runsPerOver[i]); // Call helper once per match[cite: 7]
            if (avg >= threshold) {
                result.append("Match ").append(i).append(": Power Surge");
            } else {
                result.append("Match ").append(i).append(": Normal");
            }
            if (i < runsPerOver.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }
}

// 5. Fantasy League Auto-Draft Ranking Engine
class Player implements Comparable<Player> {
    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    // Matches-played-only rule for established players (e.g., >= 10 matches)[cite: 7]
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    // Combined matches-and-fitness rule for others (e.g., >= 5 matches and not injured)[cite: 7]
    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    @Override
    public int compareTo(Player other) {
        // Rank by fantasy points (battingAverage) descending[cite: 7]
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    static String draftAndRank(Player[] players) {
        int count = 0;
        for (Player p : players) {
            if (isDraftable(p.matchesPlayed) || isDraftable(p.matchesPlayed, p.injured)) {
                count++;
            }
        }

        Player[] draftable = new Player[count];
        int index = 0;
        for (Player p : players) {
            if (isDraftable(p.matchesPlayed) || isDraftable(p.matchesPlayed, p.injured)) {
                draftable[index++] = p;
            }
        }

        Arrays.sort(draftable); // Sort handles ranking based on compareTo logic[cite: 7]

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < draftable.length; i++) {
            result.append((i + 1)).append(". ").append(draftable[i].name);
            if (i < draftable.length - 1) {
                result.append(" | ");
            }
        }
        return result.toString();
    }
}