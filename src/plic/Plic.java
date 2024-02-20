package plic;

import plic.analyse.AnalyseurSyntaxique;
import plic.analyse.ErreurSyntaxique;

import java.io.File;
import java.io.FileNotFoundException;

public class Plic {
    public static void main(String[] args) {
        if (args.length ==  0) {
            System.out.println("Usage: java Plic <fichier.plic>");
            return;
        }

        try {
            new Plic(args[0]);
        } catch (FileNotFoundException e) {
            System.out.println("Fichier source absent");
        } catch (RuntimeException e) {
            System.out.println("ERREUR: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Plic(String nomFichier) throws FileNotFoundException {
        File file = new File(nomFichier);

        if (!file.exists()) {
            throw new FileNotFoundException("Fichier source absent");
        }

        AnalyseurSyntaxique as = new AnalyseurSyntaxique(file);

        try {
            as.analyse();
        } catch (ErreurSyntaxique e) {
            throw new RuntimeException("ERREUR: " + e.getMessage());
        }
    }
}
