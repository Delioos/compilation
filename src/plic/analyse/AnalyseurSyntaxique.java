package plic.analyse;


import java.io.File;

public class AnalyseurSyntaxique {
    private AnalyseurLexical analex;
    private String uniteCourante;


    public AnalyseurSyntaxique(File file) {
        this.analex = new AnalyseurLexical(file);
    }

    /**
     * Vérifier la syntaxe du programme
     * // la représentation intermédaire sera construite plus tard
     * @throws ErreurSyntaxique
     */
    public void analyse() throws ErreurSyntaxique {
        // Demander la construction  de la première unité lexicale
        this.uniteCourante = this.analex.next();
        this.analyseProg();
        if (!this.uniteCourante.equals("EOF")) {
            throw new ErreurSyntaxique("Erreur: fin de programme attendue");
        }
    }

    private void analyseProg() throws ErreurSyntaxique {
        if (!this.uniteCourante.equals("programme")) {
            throw new ErreurSyntaxique("programme attendu");
        }
        this.uniteCourante = this.analex.next();

        if(!this.estIdf())
            throw new ErreurSyntaxique("idf attendu");
        this.uniteCourante = this.analex.next();

        this.analyseBloc();
    }

    private void analyseTerminale(String terminal) throws ErreurSyntaxique {
        if (!this.uniteCourante.equals(terminal)) {
            throw new ErreurSyntaxique(terminal + " attendu");
        }
        this.uniteCourante = this.analex.next();
    }

    private void analyseBloc() throws ErreurSyntaxique {
        this.analyseTerminale("{");
        // Iterer sur analyseDeclaration tant qu'il y a des declaraiton
        this.analyseInstruction(); // au moins une instruction
        // Iterer sur analyseInstruction tant qu'il y a des instructions
        this.analyseTerminale("}");
    }
    private boolean estIdf() {
        // regex pour identifier les identifiants
        return this.uniteCourante.matches("[a-zA-Z][a-zA-Z]*");
    }

    private boolean estCste() {
        return this.uniteCourante.matches("\\d+");
    }

    private void analyseInstruction() throws ErreurSyntaxique {
        if (this.uniteCourante.equals("ecrire")) {
            this.analyseEcrire();
        } else if (this.uniteCourante.equals(":=")) {
            this.analyseAffection();
        } else {
            throw new ErreurSyntaxique("Instruction invalide");
        }
    }
    private void analyseEcrire() throws  ErreurSyntaxique {
        this.analyseTerminale("ecrire");
        this.analyseExpression();
        this.analyseTerminale(";");
    }
    private void analyseAffection() throws ErreurSyntaxique {
        this.analyseAcces();
        this.analyseTerminale(":=");
        this.analyseExpression();
        this.analyseTerminale(";");
    }
    private void analyseAcces() throws ErreurSyntaxique {
        if (!this.estIdf()) {
            throw new ErreurSyntaxique("idf attendu");
        }
        this.uniteCourante = this.analex.next();
    }

    private void analyseExpression() throws ErreurSyntaxique {
        this.analyseOperande();
    }

    private void analyseOperande() throws ErreurSyntaxique  {
        if (this.estCste()) {
            this.uniteCourante = this.analex.next();
        } else if (this.estIdf()) {
            this.uniteCourante = this.analex.next();
        } else {
            throw new ErreurSyntaxique("Operande invalide");
        }
    }


}

