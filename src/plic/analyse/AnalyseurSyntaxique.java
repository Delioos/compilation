package plic.analyse;


import java.io.File;

public class AnalyseurSyntaxique {
    private AnalyseurLexical analex;
    private String uniteCourante;


    public AnalyseurSyntaxique(File file) {
        analex = new AnalyseurLexical(file);
    }

    /**
     * Vérifier la syntaxe du programme
     * // la représentation intermédaire sera construite plus tard
     * @throws ErreurSyntaxique
     */
    public void analyse() throws ErreurSyntaxique {
        //
    }
}
