package plic;

import plic.analyse.AnalyseurSyntaxique;
import plic.analyse.ErreurSyntaxique;

import java.io.File;
import java.util.MissingFormatArgumentException;

public class Plic {
    public static void main (String[] args) {
        try {
            new Plic(args[0]);
        } catch (MissingFormatArgumentException e) {
            System.out.println("Usage: java Plic <fichier.plic>");
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Plic(String nomFichier) {
        try {
            File file = new File(nomFichier) ; // Créer l’analyseur syntaxique
            AnalyseurSyntaxique as = new AnalyseurSyntaxique(file) ;
            // Analyse syntaxique du texte source
            try {
                as.analyse() ;
            } catch (ErreurSyntaxique e) {
                throw new RuntimeException(e);
            }
        }
        catch (Exception e) {
            // proly a FileNotFound
            e.printStackTrace() ;
        }
    }
}
