package main;

import entity.Personagem;

public class ChecarColisao {

    PainelDeControle p;

    public ChecarColisao(PainelDeControle p){
        this.p = p;
    }

    public void checarBloco(Personagem personagem){
        int personagemEsqMundoX = personagem.mundoX + personagem.areaSolidaPerso.x;
        int personagemDirMundoX = personagem.mundoX + personagem.areaSolidaPerso.x + personagem.areaSolidaPerso.width;
        int personagemTopMundoY = personagem.mundoY + personagem.areaSolidaPerso.y;
        int personagemBaixoMundoY = personagem.mundoY + personagem.areaSolidaPerso.y + personagem.areaSolidaPerso.height;

        int persoEsqCOL = personagemEsqMundoX / p.tamBloco;
        int persoDirCOL = personagemDirMundoX / p.tamBloco;
        int persoTopLIN = personagemTopMundoY / p.tamBloco;
        int persoBaixoLIN = personagemBaixoMundoY / p.tamBloco;

        int blocoNum1, blocoNum2;

        switch (personagem.direcao){

            case "cima":
                persoTopLIN = (personagemTopMundoY - personagem.velocidade)/p.tamBloco;
                blocoNum1 = p.GB.numMapBlocos[persoEsqCOL][persoTopLIN];
                blocoNum2 = p.GB.numMapBlocos[persoDirCOL][persoTopLIN];
                    if(p.GB.bloco[blocoNum1].colisao == true || p.GB.bloco[blocoNum2].colisao == true){
                        personagem.colisaoON = true;
                    }
                break;
            case "baixo":
                persoBaixoLIN = (personagemBaixoMundoY + personagem.velocidade)/p.tamBloco;
                blocoNum1 = p.GB.numMapBlocos[persoEsqCOL][persoBaixoLIN];
                blocoNum2 = p.GB.numMapBlocos[persoDirCOL][persoBaixoLIN];
                if(p.GB.bloco[blocoNum1].colisao == true || p.GB.bloco[blocoNum2].colisao == true){
                    personagem.colisaoON = true;
                }
                break;
            case "esquerda":
                persoEsqCOL = (personagemEsqMundoX - personagem.velocidade)/p.tamBloco;
                blocoNum1 = p.GB.numMapBlocos[persoEsqCOL][persoTopLIN];
                blocoNum2 = p.GB.numMapBlocos[persoEsqCOL][persoBaixoLIN];
                if(p.GB.bloco[blocoNum1].colisao == true || p.GB.bloco[blocoNum2].colisao == true){
                    personagem.colisaoON = true;
                }
                break;
            case "direita":
                persoDirCOL = (personagemDirMundoX + personagem.velocidade)/p.tamBloco;
                blocoNum1 = p.GB.numMapBlocos[persoDirCOL][persoTopLIN];
                blocoNum2 = p.GB.numMapBlocos[persoDirCOL][persoBaixoLIN];
                if(p.GB.bloco[blocoNum1].colisao == true || p.GB.bloco[blocoNum2].colisao == true){
                    personagem.colisaoON = true;
                }
                break;

        }

    }
        public int checkObjetos(Personagem personagem) {
            int index= 999;
                for(int i=0;i<p.obj.length;i++){
                    if(p.obj[i] != null){
                        //Pegar a Area solida do personagem (para saber se ele esta no msm lugar do objeto)
                        personagem.areaSolidaPerso.x = personagem.mundoX + personagem.areaSolidaPerso.x; //[A]
                        personagem.areaSolidaPerso.y = personagem.mundoY + personagem.areaSolidaPerso.y; //[B]
                        //pegar a area solida do ojjeto
                        p.obj[i].solidArea.x = p.obj[i].mundoX + p.obj[i].solidArea.x;
                        p.obj[i].solidArea.y = p.obj[i].mundoY + p.obj[i].solidArea.y;

                        //intersects é um método de REctangle que verifica se dois rectangle colidem
                        //pra isso temos que encontrar a cordenada do personagem e dos objetos
                        if(personagem.areaSolidaPerso.intersects(p.obj[i].solidArea)){
                            System.out.println("colisão"); //TEST RETIRAR DEPOIS
                            index = i; //para enviar em qual objeto colidiu
                        }
                        //"personagem.mundoX e Y ficam mudando o tempo inteiro e ao fazer [A] e [B]
                        //pausamos isso para verificar no momento, portanto tem que retornar ao normal
                        personagem.areaSolidaPerso.x = personagem.solidAreaPadraoX;
                        personagem.areaSolidaPerso.y = personagem.solidAreaPadraoY;
                        p.obj[i].solidArea.x = p.obj[i].solidAreaPadraoX;
                        p.obj[i].solidArea.y = p.obj[i].solidAreaPadraoY;
                    }
                }

            return index;
    }

}
