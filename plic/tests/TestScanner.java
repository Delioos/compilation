package tests;

import analyse.AnalyseurLexical;

public class TestScanner {
    public static void main(String[] args) {

        final String path = "./plic/sources/smol.plic";

        AnalyseurLexical analyseur = new AnalyseurLexical(path);
        analyseur.afficherMotsCles();
    }
}
