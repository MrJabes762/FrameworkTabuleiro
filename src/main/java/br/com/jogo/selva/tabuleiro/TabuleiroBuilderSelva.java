package main.java.br.com.jogo.selva.tabuleiro;

import java.util.HashMap;
import java.util.Map;

import main.java.br.com.frameworkPpr.boardgame.game.Casa;
import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.builder.TabuleiroBuilder;
import main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.multiton.TimeMultiton;
import main.java.br.com.jogo.selva.pecas.pecas.AnimalFactory;
import main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.strategy.MovimentoStrategy;

public class TabuleiroBuilderSelva implements TabuleiroBuilder {
    private Tabuleiro tabuleiro;
    private final int largura = 7;
    private final int altura = 9;
    private final AnimalFactory factory = new AnimalFactory();

    @Override
    public void iniciarTabuleiro(int largura, int altura) {
        tabuleiro = new Tabuleiro();
        tabuleiro.inicializarCasas(this.largura, this.altura);
    }

    @Override
    public void adicionarCasas() {
        // Marca casas de água, armadilhas e tocas conforme o padrão do Selva
        for (Map.Entry<Posicao, Casa> entry : tabuleiro.getCasas().entrySet()) {
            Posicao pos = entry.getKey();
            Casa casa = entry.getValue();

            // Água: linhas 3-5, colunas 1-2 e 4-5 (0-based)
            if ((pos.getLinha() >= 3 && pos.getLinha() <= 5) &&
                ((pos.getColuna() >= 1 && pos.getColuna() <= 2) || (pos.getColuna() >= 4 && pos.getColuna() <= 5))) {
                casa.adicionarCaracteristica("tipo", "agua");
            }

            // Tocas
            if ((pos.getLinha() == 0 && pos.getColuna() == 3)) {
                casa.adicionarCaracteristica("tipo", "toca_azul");
            }
            if ((pos.getLinha() == 8 && pos.getColuna() == 3)) {
                casa.adicionarCaracteristica("tipo", "toca_vermelha");
            }

            // Armadilhas azuis
            if ((pos.getLinha() == 0 && (pos.getColuna() == 2 || pos.getColuna() == 4)) ||
                (pos.getLinha() == 1 && pos.getColuna() == 3)) {
                casa.adicionarCaracteristica("tipo", "armadilha_azul");
            }

            // Armadilhas vermelhas
            if ((pos.getLinha() == 8 && (pos.getColuna() == 2 || pos.getColuna() == 4)) ||
                (pos.getLinha() == 7 && pos.getColuna() == 3)) {
                casa.adicionarCaracteristica("tipo", "armadilha_vermelha");
            }
        }
    }

    @Override
    public void adicionarPecas() {
        // Estratégias de movimento
        MovimentoStrategy movSimples = new MovimentoSimples();
        MovimentoStrategy movTigreLeao = new MovimentoTigreLeao();
        MovimentoStrategy movRato = new MovimentoRato();

        // Time Azul (Topo)
        tabuleiro.colocarPeca(factory.criarPeca("Leão", TimeMultiton.AZUL, movTigreLeao, new HashMap<>()), new Posicao(0, 0));
        tabuleiro.colocarPeca(factory.criarPeca("Tigre", TimeMultiton.AZUL, movTigreLeao, new HashMap<>()), new Posicao(0, 6));
        tabuleiro.colocarPeca(factory.criarPeca("Cão", TimeMultiton.AZUL, movSimples, new HashMap<>()), new Posicao(1, 1));
        tabuleiro.colocarPeca(factory.criarPeca("Gato", TimeMultiton.AZUL, movSimples, new HashMap<>()), new Posicao(1, 5));
        tabuleiro.colocarPeca(factory.criarPeca("Lobo", TimeMultiton.AZUL, movSimples, new HashMap<>()), new Posicao(2, 2));
        tabuleiro.colocarPeca(factory.criarPeca("Leopardo", TimeMultiton.AZUL, movSimples, new HashMap<>()), new Posicao(2, 4));
        tabuleiro.colocarPeca(factory.criarPeca("Elefante", TimeMultiton.AZUL, movSimples, new HashMap<>()), new Posicao(2, 6));
        tabuleiro.colocarPeca(factory.criarPeca("Raposa", TimeMultiton.AZUL, movSimples, new HashMap<>()), new Posicao(2, 0));
        tabuleiro.colocarPeca(factory.criarPeca("Rato", TimeMultiton.AZUL, movRato, new HashMap<>()), new Posicao(2, 6));

        // Time Vermelho (Base)
        tabuleiro.colocarPeca(factory.criarPeca("Leão", TimeMultiton.VERMELHO, movTigreLeao, new HashMap<>()), new Posicao(8, 6));
        tabuleiro.colocarPeca(factory.criarPeca("Tigre", TimeMultiton.VERMELHO, movTigreLeao, new HashMap<>()), new Posicao(8, 0));
        tabuleiro.colocarPeca(factory.criarPeca("Cão", TimeMultiton.VERMELHO, movSimples, new HashMap<>()), new Posicao(7, 5));
        tabuleiro.colocarPeca(factory.criarPeca("Gato", TimeMultiton.VERMELHO, movSimples, new HashMap<>()), new Posicao(7, 1));
        tabuleiro.colocarPeca(factory.criarPeca("Lobo", TimeMultiton.VERMELHO, movSimples, new HashMap<>()), new Posicao(6, 4));
        tabuleiro.colocarPeca(factory.criarPeca("Leopardo", TimeMultiton.VERMELHO, movSimples, new HashMap<>()), new Posicao(6, 2));
        tabuleiro.colocarPeca(factory.criarPeca("Elefante", TimeMultiton.VERMELHO, movSimples, new HashMap<>()), new Posicao(6, 0));
        tabuleiro.colocarPeca(factory.criarPeca("Raposa", TimeMultiton.VERMELHO, movSimples, new HashMap<>()), new Posicao(6, 6));
        tabuleiro.colocarPeca(factory.criarPeca("Rato", TimeMultiton.VERMELHO, movRato, new HashMap<>()), new Posicao(6, 0));
    }

    @Override
    public Tabuleiro getResultado() {
        return tabuleiro;
    }
}