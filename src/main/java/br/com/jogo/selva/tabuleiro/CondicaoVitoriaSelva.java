package main.java.br.com.jogo.selva.tabuleiro;

import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer.CondicaoDeVitoria;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.PecaFlyweight;

import java.util.Map;

public class CondicaoVitoriaSelva implements CondicaoDeVitoria {
    private final Tabuleiro tabuleiro;
    private final Map<TimeMultiton, Posicao> tocasPorTime;

    public CondicaoVitoriaSelva(Tabuleiro tabuleiro, Map<TimeMultiton, Posicao> tocasPorTime) {
        this.tabuleiro = tabuleiro;
        this.tocasPorTime = tocasPorTime;
    }

    @Override
    public TimeMultiton verificarVencedor() {
        // Para cada time, verifica se a toca foi ocupada por uma peça do adversário
        for (Map.Entry<TimeMultiton, Posicao> entry : tocasPorTime.entrySet()) {
            TimeMultiton donoDaToca = entry.getKey();
            Posicao posToca = entry.getValue();
            PecaFlyweight pecaNaToca = tabuleiro.getPecaNaPosicao(posToca);
            if (pecaNaToca != null && !pecaNaToca.getTime().equals(donoDaToca)) {
                // O adversário ocupou a toca, é o vencedor
                return (TimeMultiton) pecaNaToca.getTime();
            }
        }
        return null; // Nenhum vencedor ainda
    }
}