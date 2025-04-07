package main;

import objetos.OBJ_pocao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InterfaceUsuario {
    PainelDeControle p;
    Graphics2D g2;
    Font ariel_40;
    BufferedImage pocaoIMG;

    int tempoDeMensagem=0;
    public boolean mensagemON = false;
    public String mensagem = "";

    public int numComando;
    public int numComandoPers;
    public int numOPCpause;
    public int numOPCfimDeJogo;



    //CONSTRUTOR
    InterfaceUsuario(PainelDeControle p){
        this.p = p;
        ariel_40 = new Font("Ariel",Font.BOLD,40);
        OBJ_pocao pocao = new OBJ_pocao();
        pocaoIMG = pocao.image;
        this.numComando=0;
        this.numComandoPers=0;
        this.numOPCpause =0;
    }

    public void mostraMensagem(String texto){
        mensagem = texto;
        mensagemON = true;
    }

    public void drawSobreoJogo (Graphics2D g2){


        this.g2=g2;
        g2.setFont(ariel_40);
        g2.setColor(Color.black);

        //MENU STATE
        if(p.gameState == p.menuState){
            drawTelaDeMenu();
        }

        //ESCOLHA DE PERSONAGEM STATE
        if(p.gameState == p.escPersoState){
            drawEscolhaPersonagem();
        }

            //PLAY STATE
        if(p.gameState == p.playState){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
            g2.setColor(Color.yellow);
            g2.drawString("VIDA="+p.jogador.vida,30, 40);
            g2.drawString("ATQ= "+p.jogador.ataque,30, 80);
            g2.drawString("DEF= "+p.jogador.defesa,30, 120);

            g2.setFont(g2.getFont().deriveFont(Font.ITALIC,20F));
            g2.setColor(Color.white);
            g2.drawString("Mov-> W , S , A , D , P-> Pause",210,550);

            if(mensagemON == true){
                g2.setFont(g2.getFont().deriveFont(Font.BOLD,23F));
                g2.setColor(Color.red);
                g2.drawString(mensagem,(p.larguraTela/2)-50, p.alturaTela/2);
                tempoDeMensagem++;
                if(p.qualOBJ== p.objBomba || p.qualOBJ== p.objPocao || p.qualOBJ== p.objFogo) {
                    if (tempoDeMensagem > 100) {
                        tempoDeMensagem = 0;
                        mensagemON = false;
                    }
                } else if (p.qualOBJ== p.objMonstro || p.qualOBJ== p.objChefe) {
                    if (tempoDeMensagem > 60) {
                        tempoDeMensagem = 0;
                        mensagemON = false;
                    }
                }
            }



        }
        //PAUSE STATE
        if(p.gameState ==p.pauseState){
            drawTelaDePause();
        }
        //FIM DE JOGO
        if(p.gameState ==p.fimDeJogoState){
            drawFimDeJogo();
        }

    }

    private void drawFimDeJogo(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
        g2.setColor(Color.red);

        if(p.checkMorte==true){             //MORREU
            g2.drawString("VOCÊ MORREU :|",100,p.alturaTela/2);


        }else if (p.checkMorte==false){     //VENCEU
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60F));
            g2.setColor(Color.yellow);
            g2.drawString("YOU WIN!!! :)",100,310);

        }

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
        g2.setColor(Color.red);
        g2.drawString("Sua vida= "+p.jogador.vida +"     "+ "Vida do Chefão="+p.jogador.chefe.vidaChefe,25,370);

        int x = 350;
        int y = 395;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,45F));
        g2.setColor(Color.white);
        g2.drawString("Menu",x,y+55);
        if(numOPCfimDeJogo == 0){
            g2.drawString(">",x-p.tamBloco,y+55);
        }

        g2.drawString("QUIT",x,y+110);
        if(numOPCfimDeJogo == 1){
            g2.drawString(">",x-p.tamBloco,y+110);
        }

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,20F));
        g2.setColor(Color.white);
        g2.drawString(" W ,S ,Enter:Opções",210,560);

    }



    private  void drawTelaDePause(){
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,70F));// mudar tanho da fonte
        String text = "PAUSED";
        int x = p.larguraTela/4;
        int y = p.alturaTela/2;
        g2.drawString(text,x,y);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,45F));
        g2.setColor(Color.white);
        g2.drawString("Menu",x,y+55);
        if(numOPCpause == 0){
            g2.drawString(">",x-p.tamBloco,y+55);
        }

        g2.drawString("QUIT",x,y+110);
        if(numOPCpause == 1){
            g2.drawString(">",x-p.tamBloco,y+110);
        }

        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,20F));
        g2.setColor(Color.white);
        g2.drawString("P : Pause e Despause ; w ,S ,Enter:Opções",210,550);

    }

//PREPARAÇÃO PARA DRAWTELAMENU
BufferedImage imgMenu;  // BufferdImage obriga utilizar try catch
{
    try {
            imgMenu = ImageIO.read(getClass().getResourceAsStream("/imagens/imgMenu.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
}
 public void drawTelaDeMenu(){

        //imagem do jogo
        int x= 0;
        int y = 0;
        g2.drawImage(imgMenu,x,y,p.larguraTela,p.alturaTela,null);

        //titulo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
        String text = "Dungeon Fighter";
        x = 65;
        y = 330;
        //sombra don titulo
        g2.setColor(Color.black);
        g2.drawString(text,x+5,y+5);
        //titulo - cor principal
        g2.setColor(Color.yellow);
        g2.drawString(text,x,y);

        //opções menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        //sombra das opçoes
        g2.setColor(Color.black);

        text ="NEW GAME";
        x=255;
        y=400;
        g2.drawString(text,x+5,y+5);

        text ="DEBUG";
        x=300;
        y=450;
        g2.drawString(text,x+5,y+5);

        text ="QUIT GAME";
        x=255;
        y=500;
        g2.drawString(text,x+5,y+5);


        //cor principal das opções
        g2.setColor(Color.yellow);
        text ="NEW GAME";
        x=255;
        y=400;
        g2.drawString(text,x,y);
        if(numComando == 0){
            g2.drawString(">",x-p.tamBloco,y);
        }

        text ="DEBUG";
        x=300;
        y=450;
        g2.drawString(text,x,y);
        if(numComando == 1){
            g2.drawString(">",x-p.tamBloco,y);
        }

        text ="QUIT GAME";
        x=255;
        y=500;
        g2.drawString(text,x,y);
        if(numComando == 2){
            g2.drawString(">",x-p.tamBloco,y);
        }

     g2.setFont(g2.getFont().deriveFont(Font.ITALIC,15F));
     g2.setColor(Color.white);
     g2.drawString("navegar pelas opções -> W , S , e pressione Enter",210,550);

    }

    public void drawEscolhaPersonagem() {
        BufferedImage arqueiro = null;
        BufferedImage cavaleiro = null;
        BufferedImage mago = null;
        try {
            arqueiro = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro F1.png"));
            cavaleiro = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro F1.png"));
            mago = ImageIO.read(getClass().getResourceAsStream("/mago/Mago F1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.yellow);
        int x = 336;
        int y = 200;
        g2.drawImage(arqueiro, x, y, p.tamBloco * 2, p.tamBloco * 2, null);
        g2.drawString("Arqueiro",x+100,y+75);
        if(numComandoPers == 1){
            g2.drawString(">",x-30,y+72);
        }

        g2.drawImage(cavaleiro, x, y+p.tamBloco*2, p.tamBloco * 2, p.tamBloco * 2, null);
        g2.drawString("Cavaleiro",x+100,y+155);
        if(numComandoPers==2){
            g2.drawString(">",x-30,y+155);
        }
        g2.drawImage(mago, x, y+p.tamBloco*4, p.tamBloco * 2, p.tamBloco * 2, null);
        g2.drawString("Mago",x+100,y+250);
        if(numComandoPers==3){
            g2.drawString(">",x-p.tamBloco,y+250);
        }
        if(numComandoPers==0){
            g2.drawString("< voltar",30,40);
        }

        //comandos
        g2.setFont(g2.getFont().deriveFont(Font.ITALIC,16F));
        g2.setColor(Color.white);
        g2.drawString("navegar pelas opções -> w,s",30,550);

    }


}
