package plic.repint;

public class Symbole {
    private String nom; // Name of the symbol
    private String type; // Type of the symbol (e.g., "int", "string", etc.)

    // Constructor
    public Symbole(String nom, String type) {
        this.nom = nom;
        this.type = type;
    }

    // Getters
    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    // Method to calculate the displacement of the symbol
    // This is a simplified example; in a real implementation, the displacement calculation
    // might depend on the type of the variable and how variables are stored in memory.
    public int getDisplacement() {
        // Assuming each "int" variable takes up 4 bytes of memory
        if (type.equals("int")) {
            return 4;
        }
        // Additional logic for other types if necessary
        return 0;
    }
}
