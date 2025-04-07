# 🏰 Dungeon Fighter - Trabalho Final de POO

Projeto desenvolvido como trabalho final da disciplina de Programação Orientada a Objetos (POO), utilizando Java com foco em conceitos como herança, encapsulamento, polimorfismo, threads e interface gráfica com Java Swing e Graphics2D.

🎯 Objetivo e Aprendizado
Embora o jogo ainda precise de melhorias para oferecer uma experiência completa e fluida ao jogador, ele cumpriu com sucesso seu propósito principal: servir como ferramenta de aprendizado.

Durante o desenvolvimento, foi possível aprofundar os conhecimentos em:

* Linguagem Java

* Programação orientada a objetos

* Manipulação de threads

* Interface gráfica com Java Swing

* Renderização com Graphics2D

* Sistema de Colisão

## 🧰 Tecnologias Utilizadas

- **Java 11+**
- **Java Swing** (interface gráfica com Graphics2D)
- **Orientação a Objetos**
- **Threads**
- **KeyListener** (controle via teclado)

## 🕹️ Controles e Dinâmica do Jogo
### 🎮 Controles
W A S D: movimentação do personagem e navegação pelos menus

Enter : Confirmar opção

p : pause e despause

### ⚔️ Combate
O combate é iniciado ao colidir com um inimigo.

Cada nova colisão (ao se mover e tocar novamente no inimigo) representa um novo "turno" de combate, onde ambos (jogador e inimigo) trocam golpes.

Esse sistema simula um combate por turnos simplificado, que está previsto para ser aprimorado em versões futuras com uma mecânica mais estruturada.

### 💎 Progressão e Recompensas
Ao derrotar um inimigo, o jogador ganha atributos, tornando-se mais forte.

Também é possível coletar itens espalhados pelo mapa para aumentar atributos adicionais (como vida, ataque ou defesa).

O jogo é vencido quando o jogador consegue derrotar o boss final, que se movimenta continuamente para a direita do mapa.

## 🔧 Melhorias Futuras
Apesar do projeto já apresentar uma base funcional, algumas melhorias podem torná-lo mais completo e interessante:

* Implementar sistema de combate por turnos, tornando as batalhas mais estratégicas;

* Adicionar movimentação dos inimigos para maior desafio e dinamismo;

* Resolver bugs identificados durante testes, melhorando a estabilidade geral do jogo;

* Adicionar detecção de eventos;

* Adicionar efeitos sonoros e música de fundo.
