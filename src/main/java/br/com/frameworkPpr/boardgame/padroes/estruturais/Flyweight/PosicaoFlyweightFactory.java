package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

import java.util.HashMap;
import java.util.Map;

public class PosicaoFlyweightFactory {
    private static final Map<String, PosicaoFlyweight> posicoes = new HashMap<>();

    public static PosicaoFlyweight getPosicao(int linha, int coluna) {
        String key = linha + "-" + coluna;
        if (!posicoes.containsKey(key)) {
            posicoes.put(key, new PosicaoFlyweight(linha, coluna));
        }
        return posicoes.get(key);
    }
    public static int getTotalPosicoes() {
        return posicoes.size();
    }
    public static Map<String, PosicaoFlyweight> getPosicoes() {
        return posicoes;
    }
    @Override
    public String toString() {
        return "PosicaoFlyweightFactory []";
    }
    
}
