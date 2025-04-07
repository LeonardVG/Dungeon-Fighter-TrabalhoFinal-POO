package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Fighter");

        PainelDeControle gamePainel = new PainelDeControle();
        window.add(gamePainel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePainel.confJogo();  // adicona poções sobre o mapa e muda mexe no estado de jogo

        gamePainel.startGameThread();
    }


}
