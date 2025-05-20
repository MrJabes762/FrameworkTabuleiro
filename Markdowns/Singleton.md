# Singleton

### Intenção -

Garantir que uma classe possua apenas uma instância e provê um ponto de acesso global a ela.

### Motivação sem o Padrão -

Sem a aplicação do padrão, seria possível a criação de várias instâncias da sessão do jogo causando duplicidade e falta de controle já que haveria múltiplos jogos criados sem a centralização desses.

``` java
package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.singleton;

import main.java.br.com.frameworkPpr.boardgame.game.Jogador;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

/**
 * Classe GameSession SEM o padrão Singleton.
 * Permite múltiplas instâncias da sessão do jogo.
 */
public class GameSession {
    private Jogador jogador1;
    private Jogador jogador2;
    private Tabuleiro tabuleiro;

    /**
     * Construtor público, permitindo várias instâncias.
     * @param jogador1 Jogador 1
     * @param jogador2 Jogador 2
     * @param tabuleiro Tabuleiro do jogo
     */
    public GameSession(Jogador jogador1, Jogador jogador2, Tabuleiro tabuleiro) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
    }

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public void configurarTimes(String... nomesTimes) {
        for (String nomeTime : nomesTimes) {
            tabuleiro.registrarTime(nomeTime);
        }
    }
}
```

### UML sem singleton

![out/DiagramasIMG/motivacao_com_multiton.png](../out/DiagramasIMG/motivacao_com_multiton.png)>


### Motivação no contexto do GameSession -

Unicidade da sessão do jogo já que ele é um recurso central e único, varias instâncias dele causaria inconsistência no estado do jogo. Com o acesso global, outras partes do sistema, peças, jogadores, regras... podem interagir de forma consistente.

``` java
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
}
```

### UML com singleton

![out/DiagramasIMG/motivacao_com_singleton.png](../out/DiagramasIMG/motivacao_com_singleton.png)

### Participantes -

1. **Singleton:**
    Representado pela classe **GameSession** através de uma construtor privado, um método estático getInstance() que retorna a instância única dessa classe.
2. **Client:**
    Classes que utilizam a instância única da sessão do jogo para acessar ou modificar seu estado. Exemplos de clients incluem as classes que representam os **jogadores**, **regras do jogo** e qualquer outro componente que precise interagir com a sessão centralizada do jogo. Esses clients acessam a instância de `GameSession` por meio do método estático `getInstance()`.
