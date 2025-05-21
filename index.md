---
export_on_save:
    html: true
---

# Framework de Construção de tabuleiros

**`Equipe 5 - Jabes Cajazeira, Luis Guilherme, Náthaly Brito`**

## Índice

- [Framework de Construção de tabuleiros](#framework-de-construção-de-tabuleiros)
  - [Índice](#índice)
  - [Quadro de Versionamento](#quadro-de-versionamento)
  - [Descrição](#descrição)
  - [Objetivo](#objetivo)
  - [Descrição do Processo](#descrição-do-processo)
    - [Estrutura do Repositório](#estrutura-do-repositório)
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
|      |   0    |  Inicio no projeto         |     Jabes Cajazeira            |



---

## Descrição 

O projeto em questão, foi desenvolvido pelos discentes do curso de Análise e Desenvolvimento de Sistemas: Jabes Cajazeira, Luis Guilherme e Náthaly Brito, solicitada pela disciplina de Padrões de Projetos como forma de evidenciar de forma pratica todos os conceitos referentes á matéria em questão.

---

## Objetivo 

No tocante ao objetivo, tal projeto consistia em elaborar um Framework de Tabuleiro aplicando técnicas inerentes aos Padrões de Projeto em sua maioria descritos pelo GOF [^GAMMA] e outras literaturas como [^K19] K19 - escolha dos autores, conforme o contexto e a necessidade. Além disso, como ja podemos deduzir, construir algo desta natureza requer a abstração e capacidade de diferenciar um framework de sua utilização (trazer uma referencia que diferencie), como se não bastasse isso, tal abstração deve ser a mais genérica possível, de modo a, ser aplicável a diferentes tabuleiros. 

Com base nisso, para fundamentar a sua construção, é do objetivo que seja baseado no jogo [^SELVA] Selva - devido a variedade de aplicações e possibilidade de aplicações dos padrões. Nesse contexto, o Framework deve ser funcional tanto para o que foi baseado, quanto para outro de escolha dos autores e o framework terá que se adaptar a esse novo contexto. 


  
---

## Descrição do Processo

### Estrutura do Repositório 

| Diretório  | Descrição |
| -----------| --------- |
| `src/frameworkPpr/game` | **Diretório que contem o codigo fonte da aplicação direcionado as coisas mais comuns do game ligados ao framework**|
| `src/framework/padroes` | **Diretório que contem o codigo fonte do framework com a aplicação dos padrões de projeto** |
| `src/jogo` | **Diretorio que contem o código fonte direcionado a implementação do Selva com o uso do Framework**|

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

O Framework em questão foi implementado de acordo com a interpretação dos autores contemplando ao total 13 padrões de projetos aplicados em sua construção. Dessa maneira, ele está todo elaborado em consideração ao Selva, e além disso, temos também uma aplicação sua, para a construção de um Jogo de Batalha Naval.

- [Batalha Naval](Markdowns/BatalhaNavalComOFramework.md)

## Referências

[^SELVA]: Disponivel em: https://brainking.com/pt/GameRules?tp=56

[^GAMMA]: GAMMA, Erich. et al. Padrões de projetos: Soluções reutilizáveis de software orientados a objetos Bookman editora, 2009.

[^K19]: KASPCHUK, Alexandre; PLEIN, Tiago. K19 - Design Patterns em Java. São Paulo: K19 Treinamentos, 2012.