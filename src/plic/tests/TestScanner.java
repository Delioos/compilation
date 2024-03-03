package plic.tests;

import plic.analyse.AnalyseurLexical;

import java.io.File;

public class TestScanner {
    public static void main(String[] args) {

        final String path = "./src/plic/sources/smol.plic";
        final File file = new File(path);

        AnalyseurLexical analyseur = new AnalyseurLexical(file);
        analyseur.readFile(file);
        analyseur.afficherMotsCles();
    }
}
