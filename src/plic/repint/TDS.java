package plic.repint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TDS {
    private Map<String, Symbole> symboles;
    private Set<String> identifiants;
    private int cptDepl;

    private TDS() {
        this.symboles = new HashMap<>();
        this.identifiants = new HashSet<>();
        this.cptDepl = 0;
    }

    // singleton
    private static TDS instance;

    public static TDS getInstance() {
        if (instance == null) {
            instance = new TDS();
        }
        return instance;
    }

    public void ajouter(String idf, Symbole symbole) throws DoubleDeclaration {
        if (!identifiants.add(idf)) {
            throw new DoubleDeclaration("Erreur: Déclaration double de " + idf);
        }
        symboles.put(idf, symbole);
        cptDepl += symbole.getDeplacement();
    }

    public Symbole getSymbole(String idf) {
        return symboles.get(idf);
    }

    public int getCptDepl() {
        return cptDepl;
    }
}
