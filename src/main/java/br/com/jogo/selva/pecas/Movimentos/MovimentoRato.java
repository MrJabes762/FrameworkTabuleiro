import java.util.ArrayList;
import java.util.List;

import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.strategy.MovimentoStrategy;

public class MovimentoRato implements MovimentoStrategy {
    @Override
    public List<Posicao> calcularMovimentosPossiveis(Posicao atual, Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();
        for (Direcao dir : Direcao.ortogonais()) {
            Posicao destino = atual.mover(dir);
            if (!tabuleiro.estaDentro(destino)) continue;
            Peca pecaDestino = tabuleiro.getPeca(destino);

            // Pode entrar em água ou terra
            if (tabuleiro.ehAgua(destino) || tabuleiro.ehTerra(destino)) {
                // Se for capturar elefante, ambos precisam estar em terra
                if (pecaDestino != null && pecaDestino.getNome().equalsIgnoreCase("Elefante")) {
                    if (tabuleiro.ehTerra(atual) && tabuleiro.ehTerra(destino)) {
                        movimentos.add(destino);
                    }
                }
                // Se for sair da água, só pode capturar rato
                else if (tabuleiro.ehAgua(atual) && tabuleiro.ehTerra(destino)) {
                    if (pecaDestino == null || pecaDestino.getNome().equalsIgnoreCase("Rato")) {
                        movimentos.add(destino);
                    }
                }
                // Movimento normal (andar ou capturar qualquer peça exceto elefante)
                else if (pecaDestino == null || !pecaDestino.getNome().equalsIgnoreCase("Elefante")) {
                    movimentos.add(destino);
                }
            }
        }
        return movimentos;
    }

    @Override
    public String toString() {
        return "MovimentoRato []";
    }
    
}