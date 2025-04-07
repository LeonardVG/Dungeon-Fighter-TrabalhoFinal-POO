package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Comandos implements KeyListener {

    public boolean cimaPressionado,baixoPressionado,direitaPressionado,esquerdaPressionado;
    public PainelDeControle p;

        //CONSTRUTOR
        public Comandos(PainelDeControle p){
         this.p = p;
        }


    @Override
    public void keyTyped(KeyEvent e) {  //método obrigatorio pois é abstract de KeyListener
    }

    @Override
    public void keyPressed(KeyEvent e) { //método obrigatorio pois é abstract de KeyListener
        int code = e.getKeyCode(); //getKeyCode retorna tecla pressionada pelo usuario
        //MENU STATE
        if(p.gameState == p.menuState){
            if (code == KeyEvent.VK_W) {
                if(p.DSJ.numComando==0){
                    p.DSJ.numComando = 0;
                }else {
                    p.DSJ.numComando--;
                }
            }
            if (code == KeyEvent.VK_S) {
                if(p.DSJ.numComando==2){
                    p.DSJ.numComando =2;
                }else {
                    p.DSJ.numComando++;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if(p.DSJ.numComando == 0){ //new game
                    p.gameState = p.escPersoState;
                }
                if(p.DSJ.numComando == 1){ //debug
                    p.gameState=p.escPersoState;
                }
                if(p.DSJ.numComando == 2){ //quit
                    System.exit(0);
                }
            }

        }
            // ESTADO DE ESCOLHA DE PERSONAGEM
        else if(p.gameState==p.escPersoState){
            if (code == KeyEvent.VK_W) {
                if(p.DSJ.numComandoPers==0){                     //botão de voltar
                    p.DSJ.numComandoPers = 0;                    //continua no botao de voltar
                }else {
                    p.DSJ.numComandoPers--;
                }
            }
            if (code == KeyEvent.VK_S) {
                if(p.DSJ.numComandoPers==3){                        // esta na ultima posição
                    p.DSJ.numComandoPers =3;                        //continua na ultima posição
                }else {
                    p.DSJ.numComandoPers++;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if(p.DSJ.numComandoPers == 0){                       //volta para o menu
                    p.gameState = p.menuState;
                }
                if(p.DSJ.numComandoPers == 1){                       //opcao arqueiro
                    p.selectPersonagem=100;
                    p.jogador.getImgemJogador();
                    p.gameState=p.distribuiPontosState;
                }
                if(p.DSJ.numComandoPers == 2){                         //opçao cavaleiro
                    p.selectPersonagem=200;
                    p.jogador.getImgemJogador();
                    p.gameState=p.distribuiPontosState;
                }
                if(p.DSJ.numComandoPers == 3){                         //opçao mago
                    p.selectPersonagem=300;
                    p.jogador.getImgemJogador();
                    p.gameState=p.distribuiPontosState;
                }
            }

        }
             // ESTADO DE PAUSE
      else if (code == KeyEvent.VK_P) {  //pausar ou continuar
         if (p.gameState == p.playState) {
              p.gameState = p.pauseState;
         } else if (p.gameState == p.pauseState) {
                p.gameState = p.playState;
           }
      }
      else if(p.gameState ==p.pauseState && p.DSJ.numOPCpause == 0) {
            if (code == KeyEvent.VK_W) {
                p.DSJ.numOPCpause = 0;
            } else if(code == KeyEvent.VK_S){
                p.DSJ.numOPCpause++;
            }else if(code == KeyEvent.VK_ENTER){
                p.gameState =p.menuState;

            }
        }
        else if(p.gameState ==p.pauseState && p.DSJ.numOPCpause == 1) {
            if (code == KeyEvent.VK_S) {
                p.DSJ.numOPCpause = 1;
            } else if(code == KeyEvent.VK_W){
                p.DSJ.numOPCpause--;
            }else if(code == KeyEvent.VK_ENTER){
                System.exit(0);

            }
        }
        //PLAY STATE
      else if(p.gameState == p.playState) {
            if (code == KeyEvent.VK_W) {
                cimaPressionado = true;
            }
            if (code == KeyEvent.VK_S) {
                baixoPressionado = true;
            }
            if (code == KeyEvent.VK_A) {
                esquerdaPressionado = true;
            }
            if (code == KeyEvent.VK_D) {
                direitaPressionado = true;
            }

      }else if(p.gameState==p.fimDeJogoState){
            if (code == KeyEvent.VK_W) {
                if(p.DSJ.numOPCfimDeJogo==0){                     //botão de voltar
                    p.DSJ.numOPCfimDeJogo = 0;                    //continua no botao de voltar
                }else {
                    p.DSJ.numOPCfimDeJogo--;
                }
            }
            if (code == KeyEvent.VK_S) {
                if(p.DSJ.numOPCfimDeJogo==1){                        // esta na ultima posição
                    p.DSJ.numOPCfimDeJogo =1;                        //continua na ultima posição
                }else {
                    p.DSJ.numOPCfimDeJogo++;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (p.DSJ.numOPCfimDeJogo == 0) {                       //volta para o menu
                    p.gameState = p.menuState;
                }
                if (p.DSJ.numOPCfimDeJogo == 1) {                       //quit
                    System.exit(0);
                }
            }

        }


    }

    @Override
    public void keyReleased(KeyEvent e) { //método obrigatorio pois é abstract de KeyListener
       //tecla liberada
        int code = e.getKeyCode();

           if (code == KeyEvent.VK_W) {
               cimaPressionado = false;
           }
           if (code == KeyEvent.VK_S) {
               baixoPressionado = false;
           }
           if (code == KeyEvent.VK_A) {
               esquerdaPressionado = false;
           }
           if (code == KeyEvent.VK_D) {
               direitaPressionado = false;
           }
       }

}
