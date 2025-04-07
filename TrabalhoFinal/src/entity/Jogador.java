package entity;

import main.Comandos;
import main.PainelDeControle;
import objetos.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Personagem {
    public final int telaX;
    public final int telaY;

    public int numDePocao = 0;
    public Arqueiro arqueiro;
    public Cavaleiro cavaleiro;
    public Mago mago;

    public double vida;
    public double ataque;
    public double defesa;

    public OBJ_pocao pocao = new OBJ_pocao();
    public OBJ_Bomba bomba = new OBJ_Bomba();
    public OBJ_Fogo fogo = new OBJ_Fogo();
    public OBJ_Monstros monstros = new OBJ_Monstros();
    public OBJ_Chefe chefe = new OBJ_Chefe();
    int tempoCombate=0;

    PainelDeControle p;
    Comandos mov;

    public Jogador(PainelDeControle p , Comandos mov){
        this.p = p;
        this.mov = mov;

        telaX = p.larguraTela/2 - (p.tamBloco/2);      //para ficar sempre no meio da tela
        telaY = p.alturaTela/2  - (p.tamBloco/2);

        areaSolidaPerso = new Rectangle();
        areaSolidaPerso.x = 8;              //distancia da lateral do bloco(48x48) para o personagem
        areaSolidaPerso.y =16;              //distancia do topo do bloco(48x48) para o personagem
        areaSolidaPerso.width = 32;         //largura q vai ficar solido o prsonagem
        areaSolidaPerso.height = 32;        ////altura q vai ficar solido o prsonagem
        //para objetos
        solidAreaPadraoX = areaSolidaPerso.x;
        solidAreaPadraoY = areaSolidaPerso.y;

        setValoresPadrao();
        getImgemJogador();

        instanciaPersonagem();
    }

    public void setValoresPadrao(){
        mundoX=p.tamBloco * 14;
        mundoY=p.tamBloco * 18;                // posição do jogador
        velocidade= 3;
        direcao = "baixo";

    }

    public void getImgemJogador(){
        instanciaPersonagem();
        switch (p.selectPersonagem) {
            case 100:
               getImagemArqueiro();
               break;
            case 200:
                getImagemCavaleiro();
                break;
            case 300:
                getImagemMago();
                break;
            default:
                throw new IllegalStateException("Personagem desconhecido: " + p.selectPersonagem);
        }

    }

    public void update(){



       if(mov.direitaPressionado==true || mov.esquerdaPressionado==true
          || mov.baixoPressionado==true || mov.cimaPressionado==true) {

           if (mov.cimaPressionado == true) {
               direcao = "cima";
           } else if (mov.baixoPressionado == true) {
               direcao = "baixo";
           } else if (mov.esquerdaPressionado == true) {
               direcao = "esquerda";
           } else if (mov.direitaPressionado == true) {
               direcao = "direita";
           }
            // CHECAR COLISÃO
           colisaoON =false;
           p.checker.checarBloco(this);

           //CHECAR COLISÃO OBJETOS
           int indexObj = p.checker.checkObjetos(this); // retorna qual objeto colidiu
            pegarObjeto(indexObj);


           //SE COLISÃO É FALSO O JOGADOR PODE SE MOVER

           if(colisaoON == false){

               switch (direcao){
                   case "cima": mundoY -= velocidade; break;
                   case "baixo": mundoY += velocidade;break;
                   case "esquerda": mundoX -= velocidade; break;
                   case "direita": mundoX += velocidade;break;
               }
           }

           //atualizar os sprites  // frames do jogo
           contSprite++;
           if (contSprite > 14) {        //update é chamado 60 ves por segundo,então irá trocar de sprite 6 vezes, 6frames
               if (numSprite == 1) {
                   numSprite = 2;
               } else if (numSprite == 2) {
                   numSprite = 1;
               }
               contSprite = 0;
           }
       }
    }

    public void pegarObjeto(int indexObj){
        if(indexObj != 999){                        //se encontrou um objeto
            String nomeDoOBJ = p.obj[indexObj].nome; // pegando qual obj é

//            System.out.println("vida:"+vida);
//            System.out.println("ataque:"+ataque);                                   ///TESTE
//            System.out.println("defesa:"+defesa);
//            System.out.println("vida monstro:"+monstros.vidaMonstro);
//            System.out.println("vida monstro:"+chefe.vidaChefe);

                switch (nomeDoOBJ){
                    case "pocao":
                            vida += pocao.plusVida;
                            p.qualOBJ=p.objPocao;
                         //   numDePocao++;
                            p.obj[indexObj] = null;
                            p.DSJ.mostraMensagem("Poção->Vida +"+pocao.plusVida);

                        break;
                    case "bomba":
                        p.qualOBJ=p.objBomba;
                        p.obj[indexObj] = null;
                        double danoBomb =(defesa/100)* bomba.dano;
                        vida -= danoBomb;
                        p.DSJ.mostraMensagem("-"+danoBomb);
                        if(vida<=0){ //morreu
                            p.checkMorte=true;
                            p.gameState=p.fimDeJogoState;
                        }
                        break;
                    case "fogo":
                        p.qualOBJ=p.objFogo;
                        p.obj[indexObj] = null;
                        double danoFogo = (defesa/100)*fogo.dano;
                        vida -= danoFogo;
                        p.DSJ.mostraMensagem("-"+danoFogo);
                        if(vida<=0){ //morreu
                            p.checkMorte=true;
                            p.gameState=p.fimDeJogoState;
                        }

                        break;
                    case "monstros":
                        p.qualOBJ=p.objMonstro;
                        if( monstros.vidaMonstro > 0 && vida>0){    //em combate ( ninguem morreu
                            combateMonstro();
                            double danoMonst=(defesa/100)* monstros.dano;                               //apenas para imprimir o valor
                            p.DSJ.mostraMensagem("-"+ danoMonst );//

                        }else if(monstros.vidaMonstro<=0 && vida>0){ // matou o monstro
                            p.obj[indexObj] = null;
                            monstros.vidaMonstro= monstros.vidaRestart;                               //restaura vida do proximo monstro
                                ataque += 25;
                                defesa += 15;
                                p.DSJ.mostraMensagem("Atq: + 25   Def: + 15");                   //bonus por ter matado omonstro

                        } else if (monstros.vidaMonstro>0 && vida <=0) { //morri
                                p.checkMorte = true;
                                p.gameState =p.fimDeJogoState;

                        } else if (monstros.vidaMonstro<=0 && vida<=0) { // dois morrerem ao mesmo tempo
                                p.checkMorte = true;
                                p.gameState =p.fimDeJogoState;
                        }

                        break;
                    case "chefe":
                        p.qualOBJ=p.objChefe;

                        if( chefe.vidaChefe > 0 && vida>0){    //em combate ( ninguem morreu ainda)
                            combateChefe();
                            double danoRecebido=(defesa/100)* chefe.dano;                               //apenas para imprimir o valor
                            p.DSJ.mostraMensagem("vida -"+ danoRecebido );//

                        }else if(chefe.vidaChefe<=0 && vida>0){ // matou o monstro
                            p.DSJ.mostraMensagem(" YOU WIN !!!!!");
                            p.obj[indexObj] = null;

                            p.gameState=p.fimDeJogoState;

                        } else if (chefe.vidaChefe>0 && vida <=0) { //morri
                            p.checkMorte = true;
                            p.gameState =p.fimDeJogoState;

                        } else if (chefe.vidaChefe<=0 && vida<=0) { // dois morrerem ao mesmo tempo
                            p.checkMorte = true;
                            p.gameState =p.fimDeJogoState;
                        }
                        break;

                }
        }

    }

    public void combateMonstro(){
        tempoCombate++;
        if(tempoCombate>3) {
            vida -= (defesa / 100) * monstros.dano;

            monstros.vidaMonstro -= ataque;
            tempoCombate = 0;
        }

    }
    public  void combateChefe(){
        tempoCombate++;
        if(tempoCombate>3) {
            vida -= (defesa / 100) * chefe.dano;
            chefe.vidaChefe -= ataque;
            tempoCombate = 0;
        }

    }





    public void desenha(Graphics2D g2){
   //     g2.setColor(Color.white);
    //    g2.fillRect(x,y,p.tamBloco,p.tamBloco); //criando um quadrado 48x48 na posição 100x100


        BufferedImage image = null;

        switch (direcao){
            case "cima":
                if(numSprite ==1) {
                    image = w1;
                }if(numSprite ==2){
                image = w2;
            }
                break;
            case "baixo":
                if(numSprite ==1) {
                    image = s1;
                }if(numSprite ==2){
                    image = s2;
            }
                break;
            case "esquerda":
                if(numSprite ==1) {
                    image = a1;
                }if(numSprite ==2){
                    image = a2;
            }
                break;
            case "direita":
                if(numSprite ==1) {
                    image = d1;
                }if(numSprite ==2){
                    image = d2;
            }
                break;
        }
        g2.drawImage(image,telaX,telaY,p.tamBloco, p.tamBloco,null);//
    }

    public void getImagemArqueiro(){
        try {
            w1 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro C1.png"));
            w2 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro C2.png"));
            s1 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro F1.png"));
            s2 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro F2.png"));
            a1 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro E1.png"));
            a2 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro E2.png"));
            d1 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro D1.png"));
            d2 = ImageIO.read(getClass().getResourceAsStream("/arqueiro/Arqueiro D2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void getImagemCavaleiro(){
        try {
            w1 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro C1.png"));
            w2 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro C2.png"));
            s1 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro F1.png"));
            s2 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro F2.png"));
            a1 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro E1.png"));
            a2 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro E2.png"));
            d1 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro D1.png"));
            d2 = ImageIO.read(getClass().getResourceAsStream("/cavaleiro/cavaleiro D2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }

    }
    public void getImagemMago(){
        try {
            w1 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago C1.png"));
            w2 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago C2.png"));
            s1 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago F1.png"));
            s2 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago F2.png"));
            a1 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago E1.png"));
            a2 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago E2.png"));
            d1 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago D1.png"));
            d2 = ImageIO.read(getClass().getResourceAsStream("/mago/Mago D2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void instanciaPersonagem(){

        if(p.selectPersonagem==100){
            arqueiro=new Arqueiro(0,0,0);
            defesa=arqueiro.defesa;
            ataque=arqueiro.ataque;
            vida=arqueiro.vida;
        }
        else if(p.selectPersonagem==200){
            cavaleiro=new Cavaleiro(0,0,0);
             defesa=cavaleiro.defesa;
             ataque=cavaleiro.ataque;
             vida=cavaleiro.vida;
        }
        else if(p.selectPersonagem ==300){
            mago=new Mago(0,0,0);
            defesa=mago.defesa;
            ataque=mago.ataque;
            vida=mago.vida;
        }
        System.out.println(p.selectPersonagem);
        System.out.println(vida);
        System.out.println(ataque);
        System.out.println(defesa);

    }


}
