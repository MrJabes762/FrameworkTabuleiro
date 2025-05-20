package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.factory;

import java.util.Map;

import main.java.br.com.frameworkPpr.boardgame.game.Peca;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.strategy.MovimentoStrategy;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;

public abstract class PecaFactory {
    // Método-fábrica abstrato, parametrizado pelo tipo e estratégia de movimento
    public abstract Peca criarPeca(String tipo, TimeMultiton time, MovimentoStrategy movimentoStrategy, Map<String, Object> caracteristicas);

    // Método concreto para criar peças genéricas (útil para extensibilidade)
    public Peca criarPecaGenerica(String nome, TimeMultiton time, MovimentoStrategy movimentoStrategy, Map<String, Object> caracteristicas) {
        return new Peca(nome, time, movimentoStrategy, caracteristicas) {
            @Override
            public void update(String evento) {
                // Implementação padrão, pode ser sobrescrita
                throw new UnsupportedOperationException("Unimplemented method 'update'");
            }
        };
    }

    
}
