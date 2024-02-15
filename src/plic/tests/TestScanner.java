package plic.tests;

import plic.analyse.AnalyseurLexical;

public class TestScanner {
    public static void main(String[] args) {

        final String path = "./src/plic/sources/smol.plic";

        AnalyseurLexical analyseur = new AnalyseurLexical(path);
        analyseur.afficherMotsCles();
    }
}
