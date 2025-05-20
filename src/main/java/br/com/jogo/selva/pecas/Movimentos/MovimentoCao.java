package main.java.br.com.jogo.selva.pecas.movimentos;

import java.util.ArrayList;
import java.util.List;

import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.strategy.MovimentoStrategy;

public class MovimentoCao implements MovimentoStrategy {
    @Override
    public List<Posicao> calcularMovimentosPossiveis(Posicao atual, Tabuleiro tabuleiro) {
        List<Posicao> movimentos = new ArrayList<>();
        for (Direcao dir : Direcao.ortogonais()) {
            Posicao destino = atual.mover(dir);
            if (tabuleiro.estaDentro(destino) && tabuleiro.ehTerra(destino)) {
                movimentos.add(destino);
            }
        }
        return movimentos;
    }

    @Override
    public String toString() {
        return "MovimentoCao []";
    }
    
}
