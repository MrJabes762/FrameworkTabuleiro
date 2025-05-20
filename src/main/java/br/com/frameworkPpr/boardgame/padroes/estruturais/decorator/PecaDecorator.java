package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.decorator;

import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

import java.util.List;
import java.util.Map;

public abstract class PecaDecorator extends Peca {
    protected Peca pecaDecorada;

    public PecaDecorator(Peca pecaDecorada) {
        this.pecaDecorada = pecaDecorada;
    }

    @Override
    public String getNome() {
        return pecaDecorada.getNome();
    }

    @Override
    public void setNome(String nome) {
        pecaDecorada.setNome(nome);
    }

    @Override
    public Map<String, Object> getCaracteristicas() {
        return pecaDecorada.getCaracteristicas();
    }

    @Override
    public void setCaracteristicas(Map<String, Object> caracteristicas) {
        pecaDecorada.setCaracteristicas(caracteristicas);
    }

    @Override
    public void adicionarCaracteristica(String chave, Object valor) {
        pecaDecorada.adicionarCaracteristica(chave, valor);
    }

    @Override
    public Object obterCaracteristica(String chave) {
        return pecaDecorada.obterCaracteristica(chave);
    }

    @Override
    public List<Posicao> obterMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        return pecaDecorada.obterMovimentosPossiveis(posicaoAtual, tabuleiro);
    }

    @Override
    public TimeMultiton getTime() {
        return pecaDecorada.getTime();
    }

    public Peca getPecaDecorada() {
        return pecaDecorada;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((pecaDecorada == null) ? 0 : pecaDecorada.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PecaDecorator other = (PecaDecorator) obj;
        if (pecaDecorada == null) {
            if (other.pecaDecorada != null)
                return false;
        } else if (!pecaDecorada.equals(other.pecaDecorada))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PecaDecorator [pecaDecorada=" + pecaDecorada + "]";
    }
    
}
