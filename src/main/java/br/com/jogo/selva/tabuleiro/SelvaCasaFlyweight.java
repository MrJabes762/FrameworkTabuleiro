package main.java.br.com.jogo.selva.tabuleiro;

import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.CasaFlyweight;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.PecaFlyweight;

public class SelvaCasaFlyweight implements CasaFlyweight {
    private final SelvaCasaType tipo;
    private final int numero;

    public SelvaCasaFlyweight(SelvaCasaType tipo, int numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    // Retorna o tipo como "cor" para compatibilidade com a interface
    @Override
    public String getCor() {
        return tipo.name();
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public boolean estaOcupada() {
        // O estado de ocupação é extrínseco e deve ser controlado fora do Flyweight
        throw new UnsupportedOperationException("Ocupação deve ser controlada externamente ao Flyweight.");
    }

    public SelvaCasaType getTipo() {
        return tipo;
    }

    @Override
    public Object getPeca() {
        // Flyweight não armazena peça, retorna sempre null
        return null;
    }

    @Override
    public void setPeca(PecaFlyweight peca) {
        // Flyweight não armazena peça, não faz nada
        // Método vazio propositalmente
    }
}