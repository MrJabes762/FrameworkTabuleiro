# Observer

### Intenção -

Define uma dependência um-para-muitos entre objetos, para que quando um objeto mudar seu estado todos os seus **dependentes** são notificados e atualizados automaticamente. Isso promove o princípio **aberto/fechado (open/closed)**, já que permite adicionar novos observadores sem modificar o sujeito.

### Motivação sem o Padrão -

Sem o uso do Observer a implementação do monitoramento e observação teria que ser direto no *Tabuleiro*, assim, ele precisaria conhecer todas as classes que dependem de mudanças no estado do jogo, como a de VitoriaDerrotaObserver e Peca. Adicionar novas classes que precisariam ser notificadas resultaria na modificação da classe *Tabuleiro* violando o princípio **open/closed**. , o Tabuleiro teria que conhecer e manipular diretamente todas as peças e suas reações a eventos

```java
package br.com.frameworkPpr.xadrez.board;

import br.com.frameworkPpr.xadrez.pieces.Peca;

public class Tabuleiro {
    private Peca[][] pecas;
    private int pecasBrancas;
    private int pecasPretas;

    public Tabuleiro() {
        pecas = new Peca[8][8];
        pecasBrancas = 16;
        pecasPretas = 16;
    }

    public void removerPeca(int x, int y) {
        Peca peca = pecas[x][y];
        if (peca != null) {
            if (peca.getTime().equals("Branco")) {
                pecasBrancas--;
            } else if (peca.getTime().equals("Preto")) {
                pecasPretas--;
            }
            pecas[x][y] = null;

            // verificação de vitória diretamente no Tabuleiro
            if (pecasBrancas == 0) {
                System.out.println("Time Preto venceu!");
            } else if (pecasPretas == 0) {
                System.out.println("Time Branco venceu!");
            }
        }
    }
}
```

### UML sem o Observer -

![out/DiagramasIMG/VitoriaDerrotaSemObserver.png](../out/DiagramasIMG/VitoriaDerrotaSemObserver.png)>


### Motivação no contexto do Tabuleiro -

Com o uso do Observer, o *Tabuleiro* notifica automaticamente os observadores (*vitoriaDerrotaObserver*, *Peca*) sobre eventos importantes. As peças implementam a interface Observer, assim elas são registradas como observadores do *Tabuleiro*. Quando uma ação acontece, o *Tabuleiro* chama o método **notificarObservadores(evento)** que percorre todos os observadores e chama o **update(evento)**.

#### Tabuleiro mantém e notifica os Observers

``` java
   private List<Observer> observadores = new ArrayList<>();

   public void adicionarObservador(Observer observador)
    {
        observadores.add(observador);
    }

    public void removerObservador(Observer observador)
    {
        observadores.remove(observador);
    }

    public void notificarObservadores(String evento)
    {
        for (Observer observador : observadores)
        {
            observador.update(evento);
        }
    }
```
#### Peças implementam o Observer
``` java
    @Override
    public void update(String evento) {
        System.out.println("Peça" + getNome() + " recebeu notificação: " + evento);

        if (evento.startsWith("peça movida")) {
            String[] partes = evento.split(":");
            String[] posicoes = partes[1].trim().split(" para ");
            Posicao origem = new Posicao(Integer.parseInt(posicoes[0].split(",")[0]), Integer.parseInt(posicoes[0].split(",")[1]));
            Posicao destino = new Posicao(Integer.parseInt(posicoes[1].split(",")[0]), Integer.parseInt(posicoes[1].split(",")[1]));

            System.out.println("Movimento detectado de " + origem + " para " + destino);
        }
    }
```

### Tabuleiro notifica as Peças e outros Observers

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

#### *VitoriaDerrotaObserver* implementa a interface Observer e é registrado como observador de *Tabuleiro*.
``` java
package main.java.br.com.frameworkPpr.boardgame.padroes.comportamentais.observer;

public interface Observer {
    void update(String evento);
}
```

``` java
public class VitoriaDerrotaObserver implements Observer, CondicaoDeVitoria{
}
```

#### O *VitoriaDerrotaObserver* é acionado à lista de observadores do *Tabuleiro*

### UML com o Observer -

![out/DiagramasIMG/estruturaVitoriaDerrotaObserver.png](../out/DiagramasIMG/estruturaVitoriaDerrotaObserver.png)>

### Participantes -

1. **Subject:** Tabuleiro -> ele é o objeto observado, mantém uma lista de observadores e notifica-os sobre mudanças no estado do jogo;
2. **Observer:** interface Observer -> ela define o as regras para objetos que desejam ser notificados sobre as mudanças.
3. **ConcreteObserver:** VitoriaDerrotaObserver, Peca -> implementa a interface Observer e reage as notificações do *Tabuleiro*.

