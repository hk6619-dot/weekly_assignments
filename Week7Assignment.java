import java.util.Arrays;

// 1. The Health Bar
class Character {
    private final int maxHealth; // Maximum health must be final, fixed when created[cite: 11]
    private int health; // Private, can't be set directly from outside[cite: 11]

    public Character(int maxHealth) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }

    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) {
            health = 0; // Clamps at 0, leaves health at exactly 0, not negative[cite: 11]
        }
    }

    public void heal(int amount) {
        health += amount;
        if (health > maxHealth) {
            health = maxHealth; // Healing past the maximum caps at the maximum[cite: 11]
        }
    }

    public int getHealth() { // Read-only way to check current health[cite: 11]
        return health;
    }
}

// 2. The Playlist
class Playlist {
    private final String[] songs;
    private int count;

    public Playlist(int maxSongs) {
        this.songs = new String[maxSongs];
        this.count = 0;
    }

    public void addSong(String title) {
        if (count < songs.length) {
            songs[count++] = title;
        }
    }

    public String[] getSongs() {
        // Returns all the songs added so far as a copy, not the original array[cite: 11]
        return Arrays.copyOf(songs, count); 
    }

    public int getSongCount() {
        return count; // Read-only count of how many songs are in the playlist[cite: 11]
    }
}

// 3. The Password Checker
class PasswordChecker {
    private final String password; // Store it privately, with no getter that returns it[cite: 11]

    public PasswordChecker(String password) {
        this.password = password; // Password itself must never be changeable after the object is created[cite: 11]
    }

    public String getStrength() {
        int length = password.length(); // Use the String length to decide the rating[cite: 11]
        if (length < 6) {
            return "Weak"; // Under 6 characters[cite: 11]
        } else if (length <= 9) {
            return "Medium"; // 6-9 characters[cite: 11]
        } else {
            return "Strong"; // 10+ characters[cite: 11]
        }
    }
}

// 4. The Traffic Light
class TrafficLight {
    private final String id; // Final ID, fixed when it's created[cite: 11]
    private String color;

    public TrafficLight(String id) {
        this.id = id;
        this.color = "RED"; // A new light starts on red[cite: 11]
    }

    public void next() {
        // Moves red -> green -> yellow -> red, in that order[cite: 11]
        if (color.equals("RED")) {
            color = "GREEN";
        } else if (color.equals("GREEN")) {
            color = "YELLOW";
        } else {
            color = "RED";
        }
    }

    public String getColor() { // Read-only way to check the current color[cite: 11]
        return color;
    }
}

// 5. The Shopping Cart
class Cart {
    private final String cartId; // Final cart ID, fixed when it's created[cite: 11]
    private final int[] prices;
    private int count;

    public Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        this.prices = new int[maxItems];
        this.count = 0;
    }

    public void addItem(int price) {
        if (count < prices.length) {
            prices[count++] = price;
        }
    }

    public int getTotal() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += prices[i];
        }
        return total; // Total sum computed on request, not stored separately[cite: 11]
    }

    public int getItemCount() {
        return count;
    }
}

public class Week7Assignment {
    public static void main(String[] args) {
        // Test calls can be placed here
    }
}