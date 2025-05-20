# Builder

### Intenção
Separa a construção de um objeto complexo da sua representação, assim o mesmo processo de construção pode criar diferentes representações.

### Motivação sem o Padrão
Sem o uso do Builder, a construção de um tabuleiro ficaria centralizada em uma única classe, no caso *Tabuleiro*. Isso traria problemas como dificuladades de reutilizar partes da construção e violação do princípio de responsabilidade única, pois o *Tabuleiro* teria que saber tanto como construir quanto como se comportar dificultando a manutenção.

### Exemplo de implementação sem o Builder:
``` java
package br.com.frameworkPpr.boardgame.game;

public class Tabuleiro {
    private int largura;
    private int altura;
    private String[][] casas;
    private String[][] pecas;

    public Tabuleiro(int largura, int altura, boolean xadrez) {
        this.largura = largura;
        this.altura = altura;
        this.casas = new String[largura][altura];
        this.pecas = new String[largura][altura];
        criarCasas();
        if (xadrez) {
            posicionarPecasXadrez();
        }
    }

    private void criarCasas() {
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                casas[i][j] = ((i + j) % 2 == 0) ? "branca" : "preta";
            }
        }
    }

    private void posicionarPecasXadrez() {
        // Lógica fixa para posicionar peças de xadrez
        pecas[0][0] = "torre preta";
        pecas[0][1] = "cavalo preto";
        pecas[7][7] = "torre branca";
    }
}
```

### UML sem builder

![out/DiagramasIMG/TabuleiroSemBuilder.png](../out/DiagramasIMG/TabuleiroSemBuilder.png)

### Motivação com o Padrão
no projeto, o Builder é usado para permitir a construção flexível de diferentes tipos de tabuleiros, separando o processo de construção da representação final do objeto. Isso facilita a criação de tabuleiros personalizados para diferentes jogos.

### A interface *TabuleiroBuilder* define os métodos necessários para construir um tabuleiro:
``` java
package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.builder;

import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

public interface TabuleiroBuilder {
    void iniciarTabuleiro(int largura, int altura);
    void adicionarPecas();
    void adicionarCasas();
    Tabuleiro getResultado();
}
```
### O *TabuleiroDirector* orquestra a construção do tabuleiro. Ele chama os métodos do builder na ordem correta para criar o tabuleiro:
``` java
package main.java.br.com.frameworkPpr.boardgame.padroes.criacionais.builder;

import main.java.br.com.frameworkPpr.boardgame.game.Tabuleiro;

public class TabuleiroDirector {
    private final TabuleiroBuilder builder;

    public TabuleiroDirector(TabuleiroBuilder builder) {
        this.builder = builder;
    }

    public Tabuleiro construir(int largura, int altura) {
        builder.iniciarTabuleiro(largura, altura);
        builder.adicionarCasas();
        builder.adicionarPecas();
        return builder.getResultado();
    }
}
```

### UML com Builder:

![out/DiagramasIMG/TabuleiroBuilder.png](../out/DiagramasIMG/TabuleiroBuilder.png)

### Participantes:

1. Builder (TabuleiroBuilder) 
    - especifica  uma  interface  abstrata  para  criação  de  partes  de  um  objeto-
produto. Nesse caso, os contratos:  iniciarTabuleiro(int largura, int altura); adicionarPecas(); e void adicionarCasas();

2. ConcreteBuilder (Tabuleiro Buider Selva)
   - constrói e monta partes do produto pela implementação da interface de TabuleiroBuilder;
   - define e mantém a representação que cria;
   - È a implementação concreta do framework


3. Director (TabuleiroDirector)
   - constrói um objeto usando a interface de Builder.
  
3.  Product ().
    - representa o objeto complexo em construção. ConcreteBuilder constrói a
representação  interna  do  produto  e  define  o  processo  pelo  qual  ele  é
montado;
    - inclui classes que definem as partes constituintes, inclusive as interfaces
para a montagem das partes no resultado final.

