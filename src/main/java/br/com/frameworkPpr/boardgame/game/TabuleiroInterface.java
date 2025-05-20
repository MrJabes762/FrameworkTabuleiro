package main.java.br.com.frameworkPpr.boardgame.game;

import java.util.Map;

import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.CasaFlyweight;
import main.java.br.com.frameworkPpr.boardgame.padroes.estruturais.flyweight.PecaFlyweight;

public interface TabuleiroInterface {
    void inicializarCasas(int linhas, int colunas);
    void colocarPeca(PecaFlyweight peca, Posicao posicao, Map<Posicao, CasaFlyweight> casasFlyweight);
    void moverPeca(Posicao origem, Posicao destino, Map<Posicao,Casa> casas);
    void removerPeca(Posicao posicao);
}
