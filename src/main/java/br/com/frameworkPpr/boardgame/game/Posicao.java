package main.java.br.com.frameworkPpr.boardgame.game;

import java.util.Objects;

import main.java.br.com.jogo.selva.pecas.movimentos.Direcao;

/**
 * Classe que representa uma posição no tabuleiro de xadrez.
 */
public class Posicao {
    private int linha;
    private int coluna;

    /**
     * Construtor que inicializa a posição com linha e coluna específicas.
     * @param linha Número da linha (0 a 7 para um tabuleiro 8x8).
     * @param coluna Número da coluna (0 a 7 para um tabuleiro 8x8).
     */
    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    // Getters e setters
    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posicao posicao = (Posicao) obj;
        return linha == posicao.linha && coluna == posicao.coluna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(linha, coluna);
    }

    @Override
    public String toString() {
        return "Posicao{" +
                "linha=" + linha +
                ", coluna=" + coluna +
                '}';
    }

    public Posicao mover(Direcao direcao) {
        return new Posicao(this.linha + direcao.getDeltaLinha(), this.coluna + direcao.getDeltaColuna());
    }

    
}
