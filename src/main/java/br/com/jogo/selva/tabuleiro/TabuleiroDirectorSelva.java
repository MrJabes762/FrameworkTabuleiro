package main.java.br.com.jogo.selva.tabuleiro;

import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.builder.TabuleiroBuilder;

public class TabuleiroDirectorSelva {
    private final TabuleiroBuilder builder;

    public TabuleiroDirectorSelva(TabuleiroBuilder builder) {
        this.builder = builder;
    }

    public Tabuleiro construirTabuleiroPadrao() {
        builder.iniciarTabuleiro(7, 9); // Selva é 7x9
        builder.adicionarCasas();       // Adiciona casas normais, água, tocas, armadilhas
        builder.adicionarPecas();       // Posiciona as peças do jogo Selva
        return builder.getResultado();
    }
}
