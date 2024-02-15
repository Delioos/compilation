package analyse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalyseurLexical {
    private List<String> motsCles;

    public AnalyseurLexical(String cheminFichier) {
        motsCles = new ArrayList<>();
        lireFichier(cheminFichier);
    }

    private void lireFichier(String cheminFichier) {
        try (Scanner scanner = new Scanner(new File(cheminFichier))) {
            Pattern patternMotCle = Pattern.compile("\\w+|[{}]"); // Inclure les crochets dans le motif

            while (scanner.hasNext()) {
                if (scanner.hasNext("//")) { // Si la prochaine entrée commence par "//", sauter la ligne entière
                    scanner.nextLine();
                    continue;
                }
                Matcher matcher = patternMotCle.matcher(scanner.next()); // Analyser chaque mot individuellement
                while (matcher.find()) {
                    motsCles.add(matcher.group());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void afficherMotsCles() {
        for (String motCle : motsCles) {
            System.out.println(motCle);
        }
    }
}
