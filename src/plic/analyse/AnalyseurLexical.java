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

    /**
     * Récuperer tout les mots clés du fichier source
     * @param File file : le fichier source
     */
    private void lireFichierWithPattern(File file) {
        try {
            scanner = new Scanner(file);
            Pattern patternMotCle = Pattern.compile("\\w+|[{}]"); // Include brackets in the pattern

            while (scanner.hasNext()) {
                if (scanner.hasNext("//")) { // Skip the whole line if the next entry starts with "//"
                    scanner.nextLine();
                    continue;
                }
                Matcher matcher = patternMotCle.matcher(scanner.next()); // Analyze each word individually
                while (matcher.find()) {
                    motsCles.add(matcher.group());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
                motsCles.add("EOF");
            }
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
                return mot;
            }
            return this.scanner.next();
        } else {
            return "EOF";
        }
    }
}