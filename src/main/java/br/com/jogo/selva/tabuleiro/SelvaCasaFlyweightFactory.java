package main.java.br.com.jogo.selva.tabuleiro;

import java.util.HashMap;
import java.util.Map;

public class SelvaCasaFlyweightFactory {
    private static final Map<String, SelvaCasaFlyweight> casas = new HashMap<>();

    public static SelvaCasaFlyweight getCasa(SelvaCasaType tipo, int numero) {
        String key = tipo.name() + "-" + numero;
        if (!casas.containsKey(key)) {
            casas.put(key, new SelvaCasaFlyweight(tipo, numero));
        }
        return casas.get(key);
    }

    public static int getTotalCasas() {
        return casas.size();
    }
}