# Command 

### Intenção
encapsular uma requisão como um objeto, assim pode-se parametrizar clientes com diferentes requisições, enfileirar ou registrar requisições e suportar operações de desfazer/refazer.

### Motivação sem o Padrão
Sem o uso do padrão, o código do cliente ficaria responsável por saber como executar cada ação, como mover a peça. Isso geraria alto acoplamento pois o cliente teria que conhecer detalhes do tabuleiro e das peças. Haveria dificuldade em desfazer e refazer pois não teria um mecanismo simples para registrar e reverter ações.

### Exemplo sem o command:
``` java
// Cliente faz tudo manualmente
tabuleiro.moverPeca(origem, destino);
// Para desfazer, teria que saber como reverter:
tabuleiro.moverPeca(destino, origem);
```
### UML sem o padrão

![out/DiagramasIMG/TabuleiroSemCommand.png](../out/DiagramasIMG/TabuleiroSemCommand.png)

### Motivação com o Padrão
No nosso projeto o Command é aplicadado na manipulação de ações do tabuleiro, como mover peças. Assim o código que solicita a ação não precisa saber vomo ela é executada. A interface Command define os métodos que são implementados pela classe MevePieceCommand.

### Definição da regra para comandos:
``` java
package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.command;

public interface Command {
    void execute();
    void undo();
}
```

### Encapsulamento da ação de mover uma peça no tabuleiro:
``` java
package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.command;

import main.java.br.com.frameworkPpr.boardgame.game.Posicao;
import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

public class MovePieceCommand implements Command {
    private Tabuleiro tabuleiro;
    private Posicao origem;
    private Posicao destino;

    public MovePieceCommand(Tabuleiro tabuleiro, Posicao origem, Posicao destino) {
        this.tabuleiro = tabuleiro;
        this.origem = origem;
        this.destino = destino;
    }

    @Override
    public void execute() {
        tabuleiro.moverPeca(origem, destino);
    }

    @Override
    public void undo() {
        tabuleiro.moverPeca(destino, origem);
    }
}
```

O *Tabuleiro* executa a lógica real de movimentação:
``` java 
   public void moverPeca(Posicao origem, Posicao destino) {
        getProxySecurityInstance().moverPeca(origem, destino, getCasas());
        Casa casaDestino = getCasas().get(destino);
        Casa casaOrigem = getCasas().get(origem);
        Peca pecaMovida = casaOrigem.getPeca();
        casaDestino.setPeca(pecaMovida);
        casaOrigem.setPeca(null);
        notificarObservadores("peça movida: " + origem.getLinha() + "," + origem.getColuna() + " para " + destino.getLinha() + "," + destino.getColuna());
    }
```

### UML com o Command 

![out/DiagramasIMG/TabuleiroComCommand.png](../out/DiagramasIMG/TabuleiroComCommand.png)

### Participantes:
1. Command (interface Command):
   - declara uma interface para a execução de uma operação.   
2. ConcreteCommand (classe MovePieceCommand)
   - define uma vinculação entre um objeto Receiver e uma ação;
   - implementa  Execute() e undo()  através  da  invocação  da(s)  correspondente(s)
operação(ões) no Receiver.
3. Receiver (classe Tabuleiro):
   - cria um objeto ConcreteCommand e estabelece o seu receptor.
4. Invoker: 
5. Client (Tauleiro Selva): 
    - sabe como executar as operações associadas a uma solicitação. Qualquer
classe pode funcionar como um Receiver.