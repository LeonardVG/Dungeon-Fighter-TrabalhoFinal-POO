package objetos;

import main.PainelDeControle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObjetos {
    public BufferedImage image;
    public String  nome;
    public boolean colisao = false;
    public int mundoX,mundoY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaPadraoX = 0;
    public int solidAreaPadraoY = 0;

    public void desenha(Graphics2D g2 , PainelDeControle p){
        int telaX = mundoX - p.jogador.mundoX + p.jogador.telaX;  //onde desenhar na tela
        int telaY = mundoY - p.jogador.mundoY +  p.jogador.telaY;  //onde desenhar na tela

        if (mundoX + p.tamBloco > p.jogador.telaX - p.jogador.telaX &&
                mundoX - p.tamBloco < p.jogador.mundoX + p.jogador.telaX &&
                mundoY + p.tamBloco > p.jogador.mundoY - p.jogador.telaY &&
                mundoY - p.tamBloco < p.jogador.mundoY + p.jogador.telaX) {

            g2.drawImage(image, telaX, telaY, p.tamBloco, p.tamBloco, null);//imprime o bloco na posição x,y
        }

    }

}
