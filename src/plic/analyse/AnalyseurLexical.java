package plic.analyse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyseurLexical {
    private List<String> motsCles;
    private Scanner scanner;

    public AnalyseurLexical(File file) {

        this.motsCles = new ArrayList<>();
        try {
            this.scanner = new Scanner(file);
            String word = "start";
            // lireFichier(cheminFichier);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void readFile(File file) {
        try {
            String word = "start";
            while (word != "EOF") {
                word = this.next();
                this.motsCles.add(word);
            }
        }
        catch (Error e) {
            System.out.println(e.getStackTrace());
        }
    }

    public void afficherMotsCles() {
        for (String motCle : motsCles) {
            System.out.println(motCle);
        }
    }

    public String next() {
        if (this.scanner.hasNext()) {
            // le nouveau mot est un commentaire
            if (this.scanner.hasNext("//")) {
                this.scanner.nextLine();
                return this.next();
            }
            // le nouveau mot est collé à un commentaire
            if (this.scanner.hasNext(".*//.*")) {
                // on recupère le mot avant le commentaire
                String mot = this.scanner.next();
                mot = mot.substring(0, mot.indexOf("//"));
                this.scanner.nextLine();

//                System.out.println("in next: " + mot);
                return mot;
            }

            String mot = this.scanner.next();
            //            System.out.println("in next: " + mot);
            return mot;
        } else {
            return "EOF";
        }

    }
}