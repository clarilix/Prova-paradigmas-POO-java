# Prova 02 de Paradigmas de Programação: POO em Java

Este projeto é a refatoração do programa em Python entregue na Prova 01 (que misturava os paradigmas **imperativo** e **funcional**) para **Programação Orientada a Objetos (POO)** em Java.

O programa lê os dados de alguns alunos (nome, duas notas e faltas), calcula a média de cada um, diz se foi aprovado ou reprovado e mostra um relatório da turma.

## Como rodar

```
javac Main.java
java Main
```

## O que mudou do Python para o Java

### Estrutura

| Python (imperativo + funcional) | Java (POO) |
|---|---|
| Aluno era uma **tupla** `(nome, n1, n2, faltas)` | Aluno é uma **classe** com atributos `private` |
| Funções soltas (`calcular_media`, `esta_aprovado`) | **Métodos** da classe `Aluno` |
| Funções que recebiam a lista de alunos (`analisar_turma`, `exibir_relatorio`) | Métodos da classe `Turma` |
| Constantes no começo do arquivo | `static final` dentro da classe `Aluno` |
| `main()` chamando as 3 etapas | Classe `Main` que cria a `Turma` e chama os métodos |

### Classes

- **Aluno**: guarda nome, notas e faltas. Sabe calcular a própria média (`calcularMedia`) e dizer se está aprovado (`estaAprovado`).
- **Turma**: guarda a lista de alunos. Carrega os dados, calcula a média da turma e a melhor média, conta aprovados/reprovados e exibe o relatório.
- **Main**: só cria a turma e manda ela carregar os dados e mostrar o relatório.

### Conceitos de POO usados

- **Encapsulamento**: os atributos do `Aluno` são `private`. Para pegar o nome, usa-se o método `getNome()`. No Python, acessava-se por índice (`aluno[0]`).
- **Classe e objeto**: cada aluno é um objeto (`new Aluno(...)`) criado a partir da classe.
- **Responsabilidade de cada classe**: o que é do aluno fica no `Aluno`, o que é da turma fica na `Turma`.

### O que saiu do código

Estas coisas eram do paradigma funcional e foram trocadas por código mais simples, no estilo de POO:

- `map`, `filter` e `reduce` → viraram laços `for`
- recursão (`maior_valor`) → virou um `for` que compara as médias
- composição de funções (`compor`) → não é necessária
- dicionário com o resultado da análise → a `Turma` calcula direto o que precisa

## Resultado

A saída do programa é a mesma do Python: relatório com a média e a situação de cada aluno, total de aprovados e reprovados, média da turma e melhor média.
