package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.memento;

import java.util.Map;

import main.java.br.com.frameworkPpr.boardgame.game.Casa;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;

public class TabuleiroMemento {
    private final Map<Posicao, Casa> casasSnapshot;

    public TabuleiroMemento(Map<Posicao, Casa> casas) {
        this.casasSnapshot = casas;
    }

    public Map<Posicao, Casa> getCasasSnapshot() {
        return casasSnapshot;
    }
}
