package main.java.br.com.jogo.selva.tabuleiro;

import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.PecaFlyweight;

public class SelvaPecaFlyweight implements PecaFlyweight {
    private final String tipo;
    private final TimeMultiton time;

    public SelvaPecaFlyweight(String tipo, TimeMultiton time) {
        this.tipo = tipo;
        this.time = time;
    }

    @Override
    public String getTipo() {
        return tipo;
    }

    @Override
    public TimeMultiton getTime() {
        return time;
    }
}