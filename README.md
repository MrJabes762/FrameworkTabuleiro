# Framework de Construção de Tabuleiros V 2.3

*`Por (Equipe 5): Jabes Cajazeira, Luis Guilherme, Náthaly Brito`*

- [Acesse Aqui](https://mrjabes762.github.io/FrameworkTabuleiro/)
  
## Índice

- [Framework de Construção de Tabuleiros V 2.3](#framework-de-construção-de-tabuleiros-v-23)
  - [Índice](#índice)
  - [Quadro de Versionamento](#quadro-de-versionamento)
  - [Descrição](#descrição)
  - [Objetivo](#objetivo)
  - [Descrição do Processo](#descrição-do-processo)
    - [Estrutura do Projeto](#estrutura-do-projeto)
    - [Padrões de Projeto](#padrões-de-projeto)
      - [Padrões Comportamentais](#padrões-comportamentais)
      - [Padrões Criacionais](#padrões-criacionais)
      - [Padrões Estruturais](#padrões-estruturais)
  - [Considerações Técnicas](#considerações-técnicas)
  - [Referências](#referências)

---

## Quadro de Versionamento 


| Data | Versão | Alteração | Autor/Revisor |
| ---- |--------|-----------|---------------|
| ---- | 1      |-----------|---------------|
|   28/03/2025  |   0    | `Inicio da Modelagem: Aplicação do Padrões Criacionais Multiton (definição dos times e movimentações) e Singleton (definição de um ponto de acesso do tabuleiro); Criação da documentação do Multiton `      |     Jabes Cajazeira            |
| 29/03/2025 | 01 | `Atualização dos Padrões; Documentação do Singleton`| Jabes Cajazeira |
| 31/03/2025 | 02 | `Modelagem das Casas, Posições, Peças, Tabuleiro - com Ações; Aplicação do Padrão Strategy (calculo de Movimentos possíveis) e Factory (criação de peças)`| Luis Guilherme |
| 02/04/2025 | 03 | `Modelagem dos Padrões Observer(gerenciamento de vitoria e derrota) + Strategy; Inclusão do Multiton ás novas logicas e padrões existentes` | Náthaly Brito e Jabes Cajazeira |
| 04/04/2025 | 04 | `Refatorações no Gerenciamento da vitoria / derrota; Otimização de Lógicas do Tabuleiro; Finalização das Adaptações do Multiton`| Jabes Cajazeira |
| 10/04/2025 | 05 | `Refatorações no Padrão Singleton, Aplicação do Padrão Proxy de Proteção (proteger a realização de ações do tabuleiro e validar a possibilidade das ações)`| Jabes Cajazeira 
| 13/04/2025 | 06 | `Atualização das documentações dos Padrões Existentes e Refatoração de Diretórios; Otimização de Lógicas dos Padrões, Elaboração inicial da documentação do Proxy e Revisão das Documentações`| Náthaly Brito e Jabes Cajazeira |
|--------------| 2 | --------------------------------------------| -------------------- |
| 20/04/2025 | 00 |` Refatoração de diretórios do Projeto, Implementação de Novos Padrões: Builder (Construção do Tabuleiro), Command (Padronização dos Movimentos), Memento (Salva e Recuperação Tabuleiro), Decorator (Decoração das Peças) ; Refatoração do Multiton; Refatoração do Observer, Aplicação e Documentação do Façade; Otimização do Multiton`| Luiz Guilherme, Jabes Cajazeira e Náthaly Brito |
| 21/04/2025 | 01 | `Atualização do Proxy, Implementação inicial do Selva; Aplicação do Flyweight Pattern, State Pattern / Documentação; Clean Code, Refatoração do Singleton, Revisão da Documentação, `| Jabes Cajazeira, Náthaly Brito, Luiz Guilherme |
| 22/04/2025 | 02 | `Documentação do Decorator, Atualização da Documentação dos Padrões; Atualização do Memento, Ajuste no Observer Revisão do Decorator, Atualização da Implementação do Selva; Documentação do Padrão Flyweight, Atualização Da documentação dos Padrões: Builder, Command, Façade e Memento; Otimização da verificação da vitória/derrota, Alinhamento e Formatação das Documentações Gerais  `|  Luiz Guilherme, Náthaly Brito, Jabes Cajazeira |
| **23/04/2025** | **03** | **`Atualizações na Implementação do Selva, Revisão da Documentação dos Padrões, Finalização da documentação do Batalha Naval, Adição de Códigos Boilerplate; Otimizações, Atualizações e Revisões Gerais de Códigos, Documentação inicial da Batalha Naval, Atualização do Memento; Revisão da Documentação do Framework `**| **Luiz Guilherme, Jabes Cajazeira, Náthaly Brito** |


---

## Descrição 

O projeto em questão, foi desenvolvido pelos discentes do curso de Análise e Desenvolvimento de Sistemas: Jabes Cajazeira, Luis Guilherme e Náthaly Brito, solicitada como Trabalho final da disciplina de Padrões de Projetos, colocando em prática todos os conceitos referentes á matéria em questão, assim como, trabalho em equipe.

---

## Objetivo 

O presente projeto tem como objetivo desenvolver um framework de tabuleiro que aplique técnicas associadas aos padrões de projeto, conforme descrito por Gamma et al. (1994) [^GAMMA], bem como outras literaturas especializadas, como os materiais da K19 Treinamentos [^K19]. A escolha dos padrões será orientada pelo contexto e pelas necessidades específicas do projeto.

É fundamental compreender a distinção entre a construção de um framework e sua utilização. Segundo a DevMedia, um framework pode ser visualizado como um "esqueleto" de uma aplicação que pode ser customizado pelo programador e aplicado a um conjunto de aplicações de um mesmo domínio. Ele descreve a arquitetura de um sistema orientado a objetos, os tipos de objetos e as interações entre os mesmos, promovendo a reutilização de subsistemas e contribuindo para uma melhor qualidade do software [^DEVFRAMEWORK].

Para fundamentar a construção do framework, será utilizado como base o jogo Selva, também conhecido como Xadrez da Selva. Este jogo oferece uma variedade de aplicações e possibilidades para a implementação de padrões de projeto [^SELVA]. O framework desenvolvido deverá ser funcional tanto para o jogo Selva quanto para outros jogos de tabuleiro escolhidos pelos autores, demonstrando sua adaptabilidade a diferentes contextos.


---

## Descrição do Processo

### Estrutura do Projeto 

| Diretório  | Descrição |
| -----------| --------- |
| `src/frameworkPpr/game` | **Diretório que contem o código fonte da aplicação direcionado as coisas mais comuns do game ligados ao framework**|
| `src/framework/padroes` | **Diretório que contem o código fonte do framework com a aplicação dos padrões de projeto** |
| `src/jogo` | **Diretório que contem o código fonte direcionado a implementação do Selva com o uso do Framework**|

### Padrões de Projeto 

Neste cenário, as abstrações foram divididas em parte framework e parte jogo concentrando boa parte dos padrões de projeto em sua construção seguindo as literaturas citadas nos objetivos.

#### Padrões Comportamentais

- [Command](Markdowns/Command.md)
- [Memento](Markdowns/Memento.md)
- [Observer](Markdowns/Observer.md)
- [State](Markdowns/State.md)
- [Strategy](Markdowns/Strategy.md)

#### Padrões Criacionais

- [Builder](Markdowns/Builder.md)
- [Factory](Markdowns/Factory.md)
- [Multiton](Markdowns/Multiton.md)
- [Singleton](Markdowns/Singleton.md)

#### Padrões Estruturais

- [Decorator](Markdowns/Decorator.md)
- [Façade](Markdowns/Façade.md)
- [Flyweight](Markdowns/Flyweight.md)
- [Proxy](Markdowns/Proxy.md)


---

## Considerações Técnicas 

O Framework em questão foi implementado de acordo com a interpretação e autenticidade dos autores contemplando ao total 13 padrões de projetos aplicados em sua construção. Dessa maneira, ele foi elaborado com base no Jogo Selva, e além disso, há também uma aplicabilidade aderente ao Jogo de Batalha Naval.

- [Batalha Naval](Markdowns/BatalhaNavalComOFramework.md)

---

## Referências

[^SELVA]: RACHUNEK, Filip. Regras do jogo: Selva. BrainKing, 2002–2025. Disponível em: https://brainking.com/pt/GameRules?tp=56. Acesso em: 22 maio 2025.

[^DEVFRAMEWORK]: DEVMEDIA. Frameworks e Padrões de Projeto. 2006. Disponível em: https://www.devmedia.com.br/frameworks-e-padroes-de-projeto/1111. Acesso em: 22 maio 2025.

[^GAMMA]: GAMMA, Erich. et al. Padrões de projetos: Soluções reutilizáveis de software orientados a objetos Bookman editora, 2009.

[^K19]: KASPCHUK, Alexandre; PLEIN, Tiago. K19 - Design Patterns em Java. São Paulo: K19 Treinamentos, 2012.

