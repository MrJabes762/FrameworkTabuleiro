package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.singleton;

import main.java.br.com.frameworkPpr.boardgame.game.Jogador;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

/**
 * Classe Singleton responsável por gerenciar a sessão do jogo.
 * Garante que apenas uma instância de GameSession exista durante a execução.
 */
public class GameSession {
    // Jogador 1 da sessão
    private Jogador jogador1;
    // Jogador 2 da sessão
    private Jogador jogador2;
    // Tabuleiro associado à sessão
    private Tabuleiro tabuleiro;
    // Instância única da sessão (Singleton)
    private static GameSession instance;

    /**
     * Construtor privado para impedir instanciação externa.
     * @param jogador1 Jogador 1
     * @param jogador2 Jogador 2
     * @param tabuleiro Tabuleiro do jogo
     */
    private GameSession(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
    }

    /**
     * Obtém a instância única de GameSession, criando-a se necessário.
     * @param jogador1 Jogador 1
     * @param jogador2 Jogador 2
     * @param tabuleiro Tabuleiro do jogo
     * @return Instância única de GameSession
     */
    public static synchronized GameSession getInstance(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
        if (instance == null) {
            instance = new GameSession(jogador1, jogador2, tabuleiro);
        }
        return instance;
    }

    /**
     * Obtém a instância única de GameSession já criada.
     * @return Instância única de GameSession
     * @throws IllegalStateException se a sessão ainda não foi inicializada
     */
    public static synchronized GameSession getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GameSession ainda não foi inicializada.");
        }
        return instance;
    }

    /**
     * Retorna o Jogador 1 da sessão.
     * @return Jogador 1
     */
    public Jogador getJogador1() {
        return jogador1;
    }

    /**
     * Retorna o Jogador 2 da sessão.
     * @return Jogador 2
     */
    public Jogador getJogador2() {
        return jogador2;
    }

    /**
     * Retorna o tabuleiro da sessão.
     * @return Tabuleiro
     */
    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    /**
     * Configura os times no tabuleiro a partir dos nomes fornecidos.
     * @param nomesTimes Lista de nomes dos times
     */
    public void configurarTimes(String... nomesTimes) {
        for (String nomeTime : nomesTimes) {
            tabuleiro.registrarTime(nomeTime);
        }
    }

    /**
     * Reseta a instância da sessão, permitindo nova inicialização.
     */
    public static synchronized void reset() {
        instance = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((jogador1 == null) ? 0 : jogador1.hashCode());
        result = prime * result + ((jogador2 == null) ? 0 : jogador2.hashCode());
        result = prime * result + ((tabuleiro == null) ? 0 : tabuleiro.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameSession other = (GameSession) obj;
        if (jogador1 == null) {
            if (other.jogador1 != null)
                return false;
        } else if (!jogador1.equals(other.jogador1))
            return false;
        if (jogador2 == null) {
            if (other.jogador2 != null)
                return false;
        } else if (!jogador2.equals(other.jogador2))
            return false;
        if (tabuleiro == null) {
            if (other.tabuleiro != null)
                return false;
        } else if (!tabuleiro.equals(other.tabuleiro))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "GameSession [jogador1=" + jogador1 + ", jogador2=" + jogador2 + ", tabuleiro=" + tabuleiro + "]";
    }

    
}
