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

    public AnalyseurLexical(String cheminFichier) {
        motsCles = new ArrayList<>();
        lireFichier(cheminFichier);
    }

    private void lireFichier(String cheminFichier) {
        try {
            scanner = new Scanner(new File(cheminFichier));
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
        if (scanner.hasNext()) {
            return scanner.next();
        } else {
            return "EOF";
        }
    }
}
