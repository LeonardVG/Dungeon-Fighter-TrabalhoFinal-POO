package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistribuirPontosIG extends JFrame {
    public PainelDeControle p;
    private int pontosTotais = 15;

    private JLabel lblPontosTotais, lblVida, lblAtaque, lblDefesa;
    private JButton btnVidaMais, btnVidaMenos, btnAtaqueMais, btnAtaqueMenos, btnDefesaMais, btnDefesaMenos, btnTerminar;

    public DistribuirPontosIG(PainelDeControle p) {
        this.p = p;

        setTitle("Distribuir Pontos");
        setSize(450, 400);  // Aumenta o tamanho para acomodar o botão central
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 3));  // Adiciona uma nova linha ao layout
        setLocationRelativeTo(null);

        lblPontosTotais = new JLabel("Pontos Totais: " + pontosTotais);
        lblVida = new JLabel("Vida: " + p.jogador.vida);
        lblAtaque = new JLabel("Ataque: " + p.jogador.ataque);
        lblDefesa = new JLabel("Defesa: " + p.jogador.defesa);

        btnVidaMais = new JButton("+");
        btnVidaMenos = new JButton("-");
        btnAtaqueMais = new JButton("+");
        btnAtaqueMenos = new JButton("-");
        btnDefesaMais = new JButton("+");
        btnDefesaMenos = new JButton("-");

        btnTerminar = new JButton("Terminar");
        btnTerminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();  // Fecha apenas a janela atual
            }
        });

        // Adicionar ações aos botões
        btnVidaMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pontosTotais > 0) {
                    p.jogador.vida++;
                    pontosTotais--;
                    atualizarLabels();
                }
            }
        });

        btnVidaMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (p.jogador.vida > 0) {
                    p.jogador.vida--;
                    pontosTotais++;
                    atualizarLabels();
                }
            }
        });

        btnAtaqueMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pontosTotais > 0) {
                    p.jogador.ataque++;
                    pontosTotais--;
                    atualizarLabels();
                }
            }
        });

        btnAtaqueMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (p.jogador.ataque > 0) {
                    p.jogador.ataque--;
                    pontosTotais++;
                    atualizarLabels();
                }
            }
        });

        btnDefesaMais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (pontosTotais > 0) {
                    p.jogador.defesa++;
                    pontosTotais--;
                    atualizarLabels();
                }
            }
        });

        btnDefesaMenos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (p.jogador.defesa > 0) {
                    p.jogador.defesa--;
                    pontosTotais++;
                    atualizarLabels();
                }
            }
        });

        // Adicionar componentes à janela
        add(new JLabel("Vida"));
        add(lblVida);
        add(btnVidaMais);
        add(btnVidaMenos);

        add(new JLabel("Ataque"));
        add(lblAtaque);
        add(btnAtaqueMais);
        add(btnAtaqueMenos);

        add(new JLabel("Defesa"));
        add(lblDefesa);
        add(btnDefesaMais);
        add(btnDefesaMenos);

        add(new JLabel(""));
        add(lblPontosTotais);
        add(new JLabel(""));

        add(new JLabel(""));  // Espaço em branco na primeira coluna
        add(btnTerminar);      // Botão Terminar no centro
        add(new JLabel(""));  // Espaço em branco na terceira coluna

        setVisible(true);

    }

    private void atualizarLabels() {
        lblPontosTotais.setText("Pontos Totais: " + pontosTotais);
        lblVida.setText("Vida: " + p.jogador.vida);
        lblAtaque.setText("Ataque: " + p.jogador.ataque);
        lblDefesa.setText("Defesa: " + p.jogador.defesa);
    }

}