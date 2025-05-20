package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.decorator;

import java.util.List;

import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

public class PecaPromovidaDecorator extends PecaDecorator {
    public PecaPromovidaDecorator(Peca pecaDecorada) {
        super(pecaDecorada);
    }

    @Override
    public List<Posicao> obterMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        // Lógica extra para peça promovida pode ser adicionada aqui
        return super.obterMovimentosPossiveis(posicaoAtual, tabuleiro);
    }

    @Override
    public void update(String evento) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public String toString() {
        return "PecaPromovidaDecorator []";
    }

}
