package plic.repint;

public class DoubleDeclaration extends Exception {
    public DoubleDeclaration(String message) {
        super(message);
    }

    public DoubleDeclaration() {
        super("ERREUR: /!\\ double declaration detectee");
    }
}
