# Decorator

### Intenção -

Permitir adicionar responsabilidades a um objeto de forma dinâmica, fornecendo uma alternativa flexível à subclasse para estender funcionalidades.

### Motivação sem o padrão -

Sem o padrão Decorator, para adicionar novos comportamentos a uma peça (por exemplo, promover um peão), seria necessário criar subclasses específicas para cada combinação de comportamentos, aumentando a complexidade e dificultando a manutenção.

```java
// Exemplo sem Decorator
public class PeaoPromovidoRainha extends Peao {
    // Implementação duplicada ou alterada para representar a promoção
}
```

### UML sem Decorator -

![out/DiagramasIMG/PecaSemDecorator.png](../out/DiagramasIMG/PecaSemDecorator.png)

### Motivação no contexto do tabuleiro

No projeto, o padrão Decorator é aplicado para permitir que peças recebam funcionalidades extras em tempo de execução, como a promoção de um peão. Isso é feito sem alterar a classe original da peça, apenas "envolvendo" a peça original com um decorador.

Exemplo de implementação real:

```java
// Decorator base
public abstract class PecaDecorator extends Peca {
    protected Peca pecaDecorada;

    public PecaDecorator(Peca pecaDecorada) {
        this.pecaDecorada = pecaDecorada;
    }

    @Override
    public String getNome() {
        return pecaDecorada.getNome();
    }
    // ...demais métodos delegados...
}

// Decorator concreto
public class PecaPromovidaDecorator extends PecaDecorator {
    public PecaPromovidaDecorator(Peca pecaDecorada) {
        super(pecaDecorada);
    }

    @Override
    public List<Posicao> obterMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        // Lógica extra para peça promovida pode ser adicionada aqui
        return super.obterMovimentosPossiveis(posicaoAtual, tabuleiro);
    }
}
```

Uso típico:

```java
Peca peao = ...; // Peça original
Peca peaoPromovido = new PecaPromovidaDecorator(peao); // Adiciona comportamento de promoção
```

### UML com Decorator

![out/DiagramasIMG/PecaDecorator.png](../out/DiagramasIMG/PecaDecorator.png)


Com o Decorator, é possível estender funcionalidades das peças de forma flexível e dinâmica, sem criar uma explosão de subclasses.

### Participantes

1. **Componente:** `Peca` — define a interface dos objetos que podem receber responsabilidades adicionais.
2. **Decorator:** `PecaDecorator` — implementa a interface de `Peca` e mantém uma referência para um objeto `Peca`.
3. **ConcreteDecorator:** `PecaPromovidaDecorator` — adiciona responsabilidades ao componente.
4. **Cliente:** Código que utiliza as peças decoradas.
