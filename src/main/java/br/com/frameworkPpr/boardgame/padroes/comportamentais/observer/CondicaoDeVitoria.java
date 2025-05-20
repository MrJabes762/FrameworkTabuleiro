package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer;

import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

//regras para vitoria e derrota personalizável 
public interface CondicaoDeVitoria {
    TimeMultiton verificarVencedor();
}
