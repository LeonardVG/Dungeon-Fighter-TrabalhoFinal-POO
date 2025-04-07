package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Personagem {
    public int mundoX,mundoY;
    public int velocidade;

    public BufferedImage w1,w2,s1,s2,a1,a2,d1,d2;
    public String direcao;

    public int contSprite = 0;
    public int numSprite = 1;

    public Rectangle areaSolidaPerso;  // Rectangle é uma classe que cria um retangulo invisivel
                                       // e que se pode definar seus.x , .y , .width , .heigth
    public int solidAreaPadraoX ;
    public int solidAreaPadraoY;

    public boolean colisaoON = false;




}
