package plic.repint;

public class Nombre extends Expression {
    private int valeur;

    public Nombre(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Nombre{" +
                "valeur=" + valeur +
                '}';
    }
}

