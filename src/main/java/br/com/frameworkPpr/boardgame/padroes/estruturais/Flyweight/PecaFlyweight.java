package main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight;

import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public interface PecaFlyweight {
    String getTipo();
    TimeMultiton getTime();
}
