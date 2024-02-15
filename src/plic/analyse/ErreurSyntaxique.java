package plic.analyse;

public class ErreurSyntaxique extends Exception {
    public ErreurSyntaxique(String message) {
        super(message);
    }

    public ErreurSyntaxique() {
        super("ERREUR SYNTAXIQUE MON BROTHER");
    }

}
