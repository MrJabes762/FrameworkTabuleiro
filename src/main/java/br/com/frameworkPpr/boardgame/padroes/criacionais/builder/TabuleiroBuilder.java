package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.builder;

import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

public interface TabuleiroBuilder {
    void iniciarTabuleiro(int largura, int altura);
    void adicionarPecas();
    void adicionarCasas();
    Tabuleiro getResultado();
}
