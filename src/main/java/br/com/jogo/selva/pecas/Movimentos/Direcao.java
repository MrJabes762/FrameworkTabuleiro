package main.java.br.com.jogo.selva.pecas.movimentos;

import java.util.Arrays;
import java.util.List;

/**
 * Representa uma direção de movimento no tabuleiro.
 * Pode ser usada para calcular movimentos ortogonais e diagonais.
 */
public enum Direcao {
    CIMA(-1, 0),
    BAIXO(1, 0),
    ESQUERDA(0, -1),
    DIREITA(0, 1),
    // Diagonais (opcional, se necessário para o jogo)
    CIMA_ESQUERDA(-1, -1),
    CIMA_DIREITA(-1, 1),
    BAIXO_ESQUERDA(1, -1),
    BAIXO_DIREITA(1, 1);

    private final int deltaLinha;
    private final int deltaColuna;

    Direcao(int deltaLinha, int deltaColuna) {
        this.deltaLinha = deltaLinha;
        this.deltaColuna = deltaColuna;
    }

    public int getDeltaLinha() {
        return deltaLinha;
    }

    public int getDeltaColuna() {
        return deltaColuna;
    }

    /**
     * Retorna todas as direções ortogonais (cima, baixo, esquerda, direita).
     */
    public static List<Direcao> ortogonais() {
        return Arrays.asList(CIMA, BAIXO, ESQUERDA, DIREITA);
    }

    /**
     * Retorna todas as direções diagonais.
     */
    public static List<Direcao> diagonais() {
        return Arrays.asList(CIMA_ESQUERDA, CIMA_DIREITA, BAIXO_ESQUERDA, BAIXO_DIREITA);
    }

    /**
     * Retorna todas as direções possíveis (ortogonais + diagonais).
     */
    public static List<Direcao> todas() {
        return Arrays.asList(values());
    }
    
}
