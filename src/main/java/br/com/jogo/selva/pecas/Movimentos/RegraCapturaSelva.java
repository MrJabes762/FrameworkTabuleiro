package main.java.br.com.jogo.selva.pecas.movimentos;

import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;

public class RegraCapturaSelva {

    /**
     * Verifica se a peça atacante pode capturar a peça alvo na posição destino.
     */
    public static boolean podeCapturar(Peca atacante, Peca alvo, Posicao destino, Tabuleiro tabuleiro) {
        if (alvo == null) return false;
        if (atacante.getTime().equals(alvo.getTime())) return false;

        String tipoAtacante = atacante.getNome();
        String tipoAlvo = alvo.getNome();

        // Exemplo: rato pode capturar elefante, mas só se não estiver na água
        if (tipoAtacante.equalsIgnoreCase("Rato") && tipoAlvo.equalsIgnoreCase("Elefante")) {
            return !tabuleiro.ehAgua(destino);
        }

        // Exemplo: rato não pode capturar outro rato se estiver na água
        if (tipoAtacante.equalsIgnoreCase("Rato") && tipoAlvo.equalsIgnoreCase("Rato")) {
            return !tabuleiro.ehAgua(destino);
        }

        // Exemplo: peças normais só capturam se força >= alvo (pode usar característica "forca")
        Integer forcaAtacante = (Integer) atacante.obterCaracteristica("forca");
        Integer forcaAlvo = (Integer) alvo.obterCaracteristica("forca");
        if (forcaAtacante != null && forcaAlvo != null) {
            // Se alvo está em armadilha, pode ser capturado por qualquer peça
            if (tabuleiro.ehArmadilha(destino)) return true;
            return forcaAtacante >= forcaAlvo;
        }

        // Outras regras específicas podem ser adicionadas aqui...

        return false;
    }

    @Override
    public String toString() {
        return "RegraCapturaSelva []";
    }
}