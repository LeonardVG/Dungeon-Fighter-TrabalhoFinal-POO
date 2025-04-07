package main;

import bloco.GerenciadorBlocos;
import entity.Jogador;
import objetos.OBJ_pocao;
import objetos.SuperObjetos;

import javax.swing.*;
import java.awt.*;

public class PainelDeControle extends JPanel implements Runnable{
    //PARAMETROS PARA TELA
    final int tam_originalObj = 16 ; //personagens, coisas do cenario terão 16x16 pixels
    final int scale = 3; //escalonar para ficar 48 pixes, melhor para monitores atuais

    public final int tamBloco = tam_originalObj * scale; //48x48
    public final int tamColuna = 16;
    public final int tamLinha = 12;
    public final int larguraTela = tamBloco * tamColuna; //768
    public final int alturaTela = tamBloco * tamLinha;  //576    resolução
    int FPS = 60;
    //esta Exibindo 16 peças 48x48 horizontalmente e 12 peças 48x48 verticalmente

         //Parametros do mapa do MUNDO
    public final int maxMundoCol = 66;
    public final int maxMundoLin = 46;
    public final int larguraMundo = tamBloco * maxMundoCol;
    public  final int alturaMundo = tamBloco * maxMundoLin;

//----------------------------------------------------------------------------
   //PARAMETROS PARA AÇÕES
    GerenciadorBlocos GB =new GerenciadorBlocos(this);
    Comandos movimento =new Comandos(this);
    public ChecarColisao checker = new ChecarColisao(this);
    public ObjetosPeloMap ativa = new ObjetosPeloMap(this);
    public InterfaceUsuario DSJ = new InterfaceUsuario(this);
//THREAD
    Thread gameThread;

     //Personagem e objeto
    public int selectPersonagem = 100; //Arqueiro padrao
    public  Jogador jogador = new Jogador(this,movimento);
    public SuperObjetos obj[] = new SuperObjetos[16];  //vetor com todo tipo de objeto (bomba ou poção) por enquanto

    //identificar qual obj para mensagem na tela
    public boolean checkMorte = false;
    public int qualOBJ = 0;
    public final int objPocao = 1;
    public final int objBomba = 2;
    public final int objFogo = 3;
    public final int objMonstro = 4;
    public final int objChefe = 5;

    //estado de jogo ou Game State
    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int escPersoState = 3;
    public final int distribuiPontosState = 4;
    public final int fimDeJogoState = 5;

            //construtor
        public PainelDeControle(){
            this.setPreferredSize(new Dimension(larguraTela,alturaTela));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true); //renderizar melhor
            this.addKeyListener(movimento);
            this.setFocusable(true);
        }


        public void confJogo(){
            ativa.setObjeto();
            gameState = menuState; // começar no menu

        }


        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
        }

    @Override
    public void run() {

            double intervaloDesenho = 1000000000/FPS; // 1s = 1Bilhao de nano segundos / 60  = 0,01666 s
            double delta = 0;                         //   ficar com 60 de FPS
            long tempoFinal = System.nanoTime();      //
            long tempoAtual;                          //

            while(gameThread != null){ //Serão feitas duas coisas : 1 verificar atualizações (coandos do usuarios) e 2 redesenhar na tela

                tempoAtual = System.nanoTime();
                delta += (tempoAtual - tempoFinal)/intervaloDesenho;
                tempoFinal = tempoAtual;

                if(delta >= 1) {
                    update();// 1 VERIFICANDO ATUALIZAÇÕEs
                    repaint(); // chama o paintCoponent //2 REDESENHANDO COM BASE NAS ATUALIZAÇÕES
                    delta--;

                }
            }

    }
    public void update(){
            if (gameState == playState) {
                jogador.update();

            }
            if (gameState == pauseState) {
                //NAO ATUALIZA NADA
            }
    }

    public void paintComponent(Graphics g){  //muda de posição na tela
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D)g ; // covertendo classe
                //MENU STATE
            if(gameState == menuState){
                DSJ.drawSobreoJogo(g2);

            } else if (gameState == escPersoState) {
                DSJ.drawSobreoJogo(g2);
            }else if(gameState == distribuiPontosState){
                DistribuirPontosIG DP = new DistribuirPontosIG(this);
                gameState=playState;
            }
            //OUTROS ESTADOS
            else {
                //blocos de fundo
                GB.desenha(g2);

                //OBJETOS DO MAPA
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        obj[i].desenha(g2, this);
                    }
                }
                //JOGADOR
                jogador.desenha(g2);
                //COIsas do inventario
                DSJ.drawSobreoJogo(g2);

                g2.dispose();
            }
    }
}
