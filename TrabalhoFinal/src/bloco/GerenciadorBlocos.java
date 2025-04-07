package bloco;

import main.PainelDeControle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GerenciadorBlocos {
    PainelDeControle p;
    public  Bloco[] bloco; //vetor com tipos de blocos
    public int numMapBlocos[][];  // contem todos os blocos do mundo

        public GerenciadorBlocos(PainelDeControle p){
            this.p = p;
            bloco=new Bloco[10];
            numMapBlocos = new int[p.maxMundoCol][p.maxMundoLin];

            getBlocoImage();
            carregaMap("/maps/mundo.txt");
        }

     public void   carregaMap(String pastaArquivo){
            try{
                InputStream le = getClass().getResourceAsStream(pastaArquivo); //InputStream classe para ler dados de arquivo  byte a byte, nesta linha carregamos o arquivo
                BufferedReader br = new BufferedReader(new InputStreamReader(le)); /* new InputStreamReader(le): Converte o InputStream (que lê bytes) em um Reader (que lê caracteres),
                                                                                   permitindo que o arquivo seja lido como texto.
                                                                                   BufferedReader, é uma classe que lê o texto de maneira
                                                                                   mais eficiente, para que leituras sucessivas sejam mais rápidas.*/

                    int col=0;
                    int linha =0;

                    while(col<p.maxMundoCol && linha<p.maxMundoLin){

                        String line = br.readLine();//lê uma linha completa e coloca em line

                         while(col<p.maxMundoCol){
                            String numeros []=line.split(" ");  //pegando cada numero lido em line, identificando com os espaços  cada numero
                             int num = Integer.parseInt(numeros[col]); // mudando string para int

                             numMapBlocos [col][linha] = num;
                             col++;
                        }
                         if(col==p.maxMundoCol){
                             col=0;
                             linha++;
                         }
                    }
                        br.close();
            }catch (Exception e){
                e.printStackTrace();
            }

     }

     public void getBlocoImage(){
        try{
            bloco[0]=new Bloco();
            bloco[0].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/Grama.png"));

            bloco[1]=new Bloco();
            bloco[1].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/terra grama.png"));

            bloco[2]=new Bloco();
            bloco[2].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/Agua.png"));
            bloco[2].colisao = true;

            bloco[3]=new Bloco();
            bloco[3].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/rocha.png"));

            bloco[4]=new Bloco();
            bloco[4].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/parede.png"));
            bloco[4].colisao = true;

            bloco[5]=new Bloco();
            bloco[5].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/barco1.png"));

            bloco[6]=new Bloco();
            bloco[6].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/barco2.png"));

            bloco[7]=new Bloco();
            bloco[7].imagem = ImageIO.read(getClass().getResourceAsStream("/Blocos/arvore.png"));
            bloco[7].colisao = true;

        }catch (IOException e){
            e.printStackTrace();
        }
     }
     public void desenha(Graphics2D g2){
            int mundoCol=0;
            int mundoLinha=0;


            while(mundoCol < p.maxMundoCol && mundoLinha < p.maxMundoLin){

                int blocoNum = numMapBlocos[mundoCol][mundoLinha];

                //Encontrar posição onde desenhar na tela

                int mundoX = mundoCol * p.tamBloco;   //posição do mapa em px
                int mundoY = mundoLinha * p.tamBloco; //posição do mpa em px
                int telaX = mundoX - p.jogador.mundoX + p.jogador.telaX;  //onde desenhar na tela
                int telaY = mundoY - p.jogador.mundoY +  p.jogador.telaY;  //onde desenhar na tela

                    if (mundoX + p.tamBloco > p.jogador.telaX - p.jogador.telaX &&
                        mundoX - p.tamBloco < p.jogador.mundoX + p.jogador.telaX &&
                        mundoY + p.tamBloco > p.jogador.mundoY - p.jogador.telaY &&
                        mundoY - p.tamBloco < p.jogador.mundoY + p.jogador.telaX) {

                        g2.drawImage(bloco[blocoNum].imagem, telaX, telaY, p.tamBloco, p.tamBloco, null);//imprime o bloco na posição x,y
                    }

                    mundoCol++;               //atualiza quantidade de blocos por mundoLinha


                if(mundoCol == p.maxMundoCol){  //se chegou no final da tela
                    mundoCol=0;               // reinicia

                    mundoLinha++;                //vai para a proxima liinha

                }

            }
     }
}
