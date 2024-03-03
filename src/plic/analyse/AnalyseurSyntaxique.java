package plic.analyse;


import java.io.File;
import plic.repint.DoubleDeclaration;
import plic.repint.Expression;
import plic.repint.Symbole;
import plic.repint.TDS;

public class AnalyseurSyntaxique {
    private AnalyseurLexical analex;
    private String uniteCourante;
    private TDS tds;


    public AnalyseurSyntaxique(File file) {
        this.analex = new AnalyseurLexical(file);
    }

    /**
     * Vérifier la syntaxe du programme
     * // la représentation intermédaire sera construite plus tard
     * @throws ErreurSyntaxique
     */
    public void analyse() throws ErreurSyntaxique, DoubleDeclaration {
        // Demander la construction  de la première unité lexicale
        this.uniteCourante = this.analex.next();
        this.analyseProg();
        if (!this.uniteCourante.equals("EOF")) {
            throw new ErreurSyntaxique("Erreur: fin de programme attendue");
        }
    }

    private void analyseProg() throws ErreurSyntaxique, DoubleDeclaration {
        if (!this.uniteCourante.equals("programme")) {
            throw new ErreurSyntaxique("ERREUR: Programme attendu");
        }
        this.uniteCourante = this.analex.next();

        if(!this.estIdf())
            throw new ErreurSyntaxique("ERREUR: Idf attendu");
        this.uniteCourante = this.analex.next();

        this.analyseBloc();
    }

    private void analyseTerminale(String terminal) throws ErreurSyntaxique {
        if (!this.uniteCourante.equals(terminal)) {
            throw new ErreurSyntaxique(terminal + " attendu");
        }
        this.uniteCourante = this.analex.next();
    }

    // Méthode adaptée pour inclure l'analyse des déclarations dans le bloc
    private void analyseBloc() throws ErreurSyntaxique, DoubleDeclaration {
        this.analyseTerminale("{");
        // Vérification et ajout des déclarations
        while (this.uniteCourante.equals("entier")) {
            this.analyseDeclaration();
        }
        // Itération sur les instructions tant qu'il y en a
        this.analyseInstruction();
        this.analyseTerminale("}");
    }

    private void analyseDeclaration() throws ErreurSyntaxique, DoubleDeclaration {
        if (!this.uniteCourante.equals("entier")) {
            throw new ErreurSyntaxique("ERREUR: Type de déclaration attendu");
        }
        this.uniteCourante = this.analex.next();

        if (!this.estIdf()) {
            throw new ErreurSyntaxique("ERREUR: Idf attendu");
        }
        String idf = this.uniteCourante;
        this.uniteCourante = this.analex.next();

        // Création du symbole et ajout à la TDS
        Symbole symbole = new Symbole(idf, "entier");
        this.tds.ajouter(idf, symbole);
    }
    
    private boolean estIdf() {
        // regex pour identifier les identifiants
        return this.uniteCourante.matches("[a-zA-Z]\\w*") && !this.analyseKeyword();
    }

    private boolean analyseKeyword() {
        return this.uniteCourante.matches("programme|ecrire|lire|entier");
    }

    private void analyseInstruction() throws ErreurSyntaxique {
        if (this.uniteCourante.equals("ecrire")){
            this.analyseEcrire();
        } else {
            this.analyseAffection();
        }
    }
    private void analyseEcrire() throws  ErreurSyntaxique {
        this.analyseTerminale("ecrire");
        Expression expression = this.analyseExpression();
        this.analyseTerminale(";");
    }
    private void analyseAffection() throws ErreurSyntaxique {
        this.analyseAcces();
        this.analyseTerminale(":=");
        Expression expression = this.analyseExpression();
        this.analyseTerminale(";");
    }
    private void analyseAcces() throws ErreurSyntaxique {
        if (!this.estIdf()) {
            throw new ErreurSyntaxique("ERREUR: Idf attendu");
        }
        this.uniteCourante = this.analex.next();
    }

    private void analyseExpression() throws ErreurSyntaxique {
        this.analyseOperande();
    }

    private void analyseOperande() throws ErreurSyntaxique {
        if (estIdf())
            this.analyseAcces();
        else {
            if (!this.estCstEntiere())
                throw new ErreurSyntaxique("ERREUR: Entier attendu");
            this.uniteCourante = this.analex.next();
        }
    }


    private boolean estCstEntiere() {
        return this.uniteCourante.matches("[0-9]+");
    }


    public String toString() {
        System.out.println(this.uniteCourante);
        return this.uniteCourante;
    }
}

