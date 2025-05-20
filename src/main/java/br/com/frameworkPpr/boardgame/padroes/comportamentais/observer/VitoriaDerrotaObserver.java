package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer;

import java.util.Objects;

import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public class VitoriaDerrotaObserver implements Observer, CondicaoDeVitoria{
    private Tabuleiro tabuleiro;

    public VitoriaDerrotaObserver(Tabuleiro tabuleiro) {
        setTabuleiro(tabuleiro);
    }

    @Override
    public TimeMultiton verificarVencedor() {
        return TimeMultiton.getTimeObjetos().stream()
            .filter(time -> time.getQuantidadePecasDoTime() == 0) // Filtra o time com 0 peças
            .findFirst() // Encontra o primeiro time com 0 peças
            .map(timeComZeroPecas -> 
                TimeMultiton.getTimeObjetos().stream()
                    .filter(outroTime -> !Objects.equals(outroTime, timeComZeroPecas)) // Filtra o outro time
                    .findFirst() // Encontra o outro time
                    .orElse(null) // Caso não encontre, retorna null
            )
            .orElse(null); // Caso nenhum time tenha 0 peças, retorna null
    }

    public void notificarVencedor(TimeMultiton vencedor)
    {
        System.out.println("Vencedor: " + vencedor);
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    @Override
    public void update(String evento) {
        TimeMultiton vencedor = verificarVencedor();
        if (vencedor != null) {
            notificarVencedor(vencedor);
        }
    }
}
