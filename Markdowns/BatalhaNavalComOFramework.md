# Criando o Jogo Batalha Naval com o Framework

## Passo 1: Configuração do Tabuleiro (Builder)

### Inicialização do Tabuleiro 

Para configurar o tabuleiro de forma flexível e modular, utilizamos o padrão **Builder**. Isso permite separar a lógica de construção do tabuleiro da sua representação final.

O **Builder** (`TabuleiroBatalhaConcreto`) é responsável por definir os detalhes do tabuleiro, enquanto o **Director** (`TabuleiroDirector`) orquestra o processo de construção.

```java
// Criação do Builder específico para o jogo Batalha Naval
TabuleiroBuilder builder = new TabuleiroBatalhaConcreto();

// O Director gerencia a construção do tabuleiro
TabuleiroDirector director = new TabuleiroDirector(builder);

// Construção do tabuleiro 10x10
Tabuleiro tabuleiro = director.construir(10, 10);
```

**Sugestão de implementação do Builder:**
- Implemente um `TabuleiroBatalhaConcreto` que define casas do tipo "água" e inicializa o grid vazio.
- No método `adicionarCasas`, marque as casas como água ou, se desejar, implemente áreas especiais (ex: porto).
- No método `adicionarPecas`, deixe vazio ou adicione navios fixos para testes.

## Passo 2: Registro dos Times (Multiton)

### Criação dos Times

Os times que participarão do jogo são registrados utilizando o padrão **Multiton**, que garante que cada time seja único dentro do contexto do jogo.

```java
TimeMultiton time1 = tabuleiro.registrarTime("Time Azul");
TimeMultiton time2 = tabuleiro.registrarTime("Time Vermelho");
```

- Cada time pode ser identificado por nome e suas embarcações associadas.

## Passo 3: Colocação das Embarcações (Factory)

### Definição de Peças (Embarcações)

As embarcações são representadas como peças no tabuleiro. Para criar essas peças, utilizamos o padrão **Factory**, que encapsula a lógica de criação.

```java
PecaFactory pecaFactory = new PecaFactory() {
    @Override
    public Peca criarPeca(String tipo, TimeMultiton time, MovimentoStrategy movimentoStrategy, Map<String, Object> caracteristicas) {
        return new Peca(tipo, time, movimentoStrategy, caracteristicas);
    }
};

// Exemplo de criação de uma embarcação
Peca destroyer = pecaFactory.criarPeca("Destroyer", time1, new MovimentoNavio(), Map.of("tamanho", 2, "vida", 2));
Peca submarino = pecaFactory.criarPeca("Submarino", time2, new MovimentoNavio(), Map.of("tamanho", 3, "vida", 3));
```

- Crie uma subclasse de `PecaFactory` se quiser lógica específica para cada tipo de navio.
- Use o campo `caracteristicas` para armazenar tamanho, vida, tipo, etc.

### Posicionamento das Embarcações

Após criar as peças, posicionamos as embarcações no tabuleiro utilizando o método `colocarPeca`.

```java
tabuleiro.colocarPeca(destroyer, new Posicao(0, 0));
tabuleiro.colocarPeca(submarino, new Posicao(5, 5));
```

- Para navios maiores, você pode criar uma lógica para ocupar múltiplas casas (ex: adicionar uma lista de posições ocupadas na peça ou marcar as casas correspondentes).

## Passo 4: Implementação das Regras de Jogo (Strategy)

### Validação de Movimentos

As regras de movimento das embarcações são implementadas utilizando o padrão **Strategy**, que permite definir diferentes estratégias de movimento.

```java
public class MovimentoNavio implements MovimentoStrategy {
    @Override
    public List<Posicao> calcularMovimentosPossiveis(Posicao posicaoAtual, Tabuleiro tabuleiro) {
        // Movimento limitado a uma casa em qualquer direção
        List<Posicao> movimentos = new ArrayList<>();
        int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] d : deltas) {
            Posicao nova = new Posicao(posicaoAtual.getLinha() + d[0], posicaoAtual.getColuna() + d[1]);
            if (tabuleiro.estaDentro(nova) && tabuleiro.getCasa(nova).isAgua()) {
                movimentos.add(nova);
            }
        }
        return movimentos;
    }
}
```

- Implemente diferentes estratégias para submarinos, encouraçados, etc., se necessário.

### Regras Específicas de Captura

No **Batalha Naval**, uma embarcação pode capturar outra se estiver na mesma posição ou em uma posição adjacente. A lógica de captura pode ser implementada da seguinte forma:

```java
if (tabuleiro.podeCapturar(new Posicao(0, 0), new Posicao(1, 0))) {
    tabuleiro.moverPeca(new Posicao(0, 0), new Posicao(1, 0));
    System.out.println("Embarcação capturada!");
}
```

- Implemente o método `podeCapturar` no Tabuleiro para verificar se há uma peça inimiga na posição de destino ou adjacente.
- Para ataques, você pode criar um método `atacar(Posicao alvo)` na peça ou no tabuleiro, reduzindo a vida da embarcação.

## Passo 5: Monitoramento do Jogo (Observer)

### Observadores

O padrão **Observer** é utilizado para monitorar eventos no tabuleiro, como movimentação ou destruição de embarcações.

```java
tabuleiro.adicionarObservador(new Observer() {
    @Override
    public void update(String evento) {
        System.out.println("Evento no tabuleiro: " + evento);
    }
});
```

- Implemente observadores para monitorar vitória, destruição de navios, fim de turno, etc.

## Passo 6: Controle de Estados do Jogo (State)

### Estados do Jogo

O padrão **State** é utilizado para gerenciar os estados do jogo, como iniciado, pausado ou finalizado.

```java
tabuleiro.iniciarJogo();
System.out.println("Jogo iniciado!");

tabuleiro.finalizarJogo();
System.out.println("Jogo finalizado!");
```

- Use o contexto de estado do framework para controlar se o jogo pode aceitar movimentos, ataques, etc.

## Passo 7: Salvamento e Restauração do Jogo (Memento)

### Salvamento do Estado 

O padrão **Memento** é utilizado para salvar o estado atual do tabuleiro, permitindo que o jogo seja restaurado posteriormente.

```java
TabuleiroMemento memento = tabuleiro.criarMemento();
System.out.println("Estado do jogo salvo!");
```

### Restauração do Estado

O estado do tabuleiro pode ser restaurado a partir de um memento, permitindo que o jogo continue de onde parou.

```java
tabuleiro.restaurarMemento(memento);
System.out.println("Estado do jogo restaurado!");
```

---

## Outras recomendações e padrões

- **Proxy**: Use o `TabuleiroProxySecurity` para validar se o movimento/ataque é permitido antes de executar.
- **Command**: Implemente comandos para ações como mover, atacar, desfazer/refazer jogadas.
- **Façade**: Crie uma fachada para expor operações de alto nível do jogo (ex: iniciar partida, atacar, verificar vitória).
- **Decorator**: Caso queira adicionar poderes temporários a navios (ex: escudo, sonar), utilize Decorator para adicionar comportamento sem alterar a classe base.

---

## Exemplo de fluxo completo

```java
// Inicialização
TabuleiroBuilder builder = new TabuleiroBatalhaConcreto();
TabuleiroDirector director = new TabuleiroDirector(builder);
Tabuleiro tabuleiro = director.construir(10, 10);

// Registro dos times
TimeMultiton azul = tabuleiro.registrarTime("Azul");
TimeMultiton vermelho = tabuleiro.registrarTime("Vermelho");

// Criação e posicionamento das embarcações
PecaFactory factory = ...; // sua implementação
Peca destroyer = factory.criarPeca("Destroyer", azul, new MovimentoNavio(), Map.of("tamanho", 2, "vida", 2));
tabuleiro.colocarPeca(destroyer, new Posicao(0, 0));

// Observador de vitória
tabuleiro.adicionarObservador(new VitoriaDerrotaObserver(tabuleiro));

// Início do jogo
tabuleiro.iniciarJogo();

// Jogada de ataque
if (tabuleiro.podeCapturar(new Posicao(0, 0), new Posicao(1, 0))) {
    tabuleiro.moverPeca(new Posicao(0, 0), new Posicao(1, 0));
}

// Salvamento do estado
TabuleiroMemento memento = tabuleiro.criarMemento();

// Restauração do estado
tabuleiro.restaurarMemento(memento);
```

---

## Resumo

O framework permite implementar Batalha Naval de forma flexível, reaproveitando padrões GoF para modularidade, manutenção e extensibilidade. Basta especializar as classes de Builder, Factory, Strategy e Observer conforme as regras do jogo desejado.

