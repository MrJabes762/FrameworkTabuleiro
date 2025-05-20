package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

import java.util.HashMap;
import java.util.Map;

public class CasaFlyweightFactory {
    private static final Map<String, CasaFlyweight> casas = new HashMap<>();

    public static CasaFlyweight getCasa(String cor, int numero) {
        if (!casas.containsKey(cor + numero)) {
            casas.put(cor + numero, new CasaConcretaFlyweight(cor, numero));
        }
        return casas.get(cor + numero);
    }
    public static int getTotalCasas() {
        return casas.size();
    }
    public static Map<String, CasaFlyweight> getCasas() {
        return casas;
    }
    @Override
    public String toString() {
        return "CasaFlyweightFactory []";
    }
    

}