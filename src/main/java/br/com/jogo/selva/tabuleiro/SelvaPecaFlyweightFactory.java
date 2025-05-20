package main.java.br.com.jogo.selva.tabuleiro;

import java.util.HashMap;
import java.util.Map;

import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public class SelvaPecaFlyweightFactory {
    private static final Map<String, SelvaPecaFlyweight> pecas = new HashMap<>();

    public static SelvaPecaFlyweight getPeca(String tipo, TimeMultiton time) {
        String key = tipo + "-" + time.toString();
        if (!pecas.containsKey(key)) {
            pecas.put(key, new SelvaPecaFlyweight(tipo, time));
        }
        return pecas.get(key);
    }

    public static int getTotalPecas() {
        return pecas.size();
    }
}