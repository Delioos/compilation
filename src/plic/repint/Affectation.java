package plic.repint;

public class Affectation extends Instruction {
    private String idf;
    private Expression valeur;

    public Affectation(String idf, Expression valeur) {
        this.idf = idf;
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Affectation{" +
                "idf='" + idf + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}