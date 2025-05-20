package main.java.br.com.jogo.selva.pecas.pecas;

import java.util.Map;
import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.strategy.MovimentoStrategy;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.factory.PecaFactory;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public class AnimalFactory extends PecaFactory {
    @Override
    public Peca criarPeca(String tipo, TimeMultiton time, MovimentoStrategy movimentoStrategy, Map<String, Object> caracteristicas) {
        // Método-fábrica parametrizado conforme contrato da superclasse
        return switch (tipo) {
            case "Elefante" -> new Elefante(tipo, time, movimentoStrategy, caracteristicas);
            case "Leão" -> new Leão(tipo, time, movimentoStrategy, caracteristicas);
            case "Tigre" -> new Tigre(tipo, time, movimentoStrategy, caracteristicas);
            case "Lobo" -> new Lobo(tipo, time, movimentoStrategy, caracteristicas);
            case "Raposa" -> new Raposa(tipo, time, movimentoStrategy, caracteristicas);
            case "Gato" -> new Gato(tipo, time, movimentoStrategy, caracteristicas);
            case "Rato" -> new Rato(tipo, time, movimentoStrategy, caracteristicas);
            default -> throw new IllegalArgumentException("Tipo de peça desconhecido: " + tipo);
        };
    }
}
