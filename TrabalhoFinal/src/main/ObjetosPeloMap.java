package main;

import objetos.*;

public class ObjetosPeloMap {

    PainelDeControle p;

        public ObjetosPeloMap(PainelDeControle p){
        this.p = p;
        }

        public void setObjeto (){
            p.obj[0] = new OBJ_pocao();
            p.obj[0].mundoX = 8 * p.tamBloco;
            p.obj[0].mundoY = 7 * p.tamBloco;

            p.obj[1] = new OBJ_pocao();
            p.obj[1].mundoX = 31 * p.tamBloco;
            p.obj[1].mundoY = 36 * p.tamBloco;

            p.obj[2] = new OBJ_pocao();
            p.obj[2].mundoX = 30 * p.tamBloco;
            p.obj[2].mundoY = 6 * p.tamBloco;

            p.obj[3] = new OBJ_pocao();
            p.obj[3].mundoX = 43 * p.tamBloco;
            p.obj[3].mundoY = 19 * p.tamBloco;

            p.obj[4] = new OBJ_Bomba();
            p.obj[4].mundoX = 32 * p.tamBloco;
            p.obj[4].mundoY = 20 * p.tamBloco;

            p.obj[5] = new OBJ_Fogo();
            p.obj[5].mundoX = 48 * p.tamBloco;
            p.obj[5].mundoY = 16 * p.tamBloco;

            p.obj[6] = new OBJ_Fogo();
            p.obj[6].mundoX = 14 * p.tamBloco;
            p.obj[6].mundoY = 9 * p.tamBloco;

            p.obj[7] = new OBJ_Monstros();
            p.obj[7].mundoX = 25 * p.tamBloco;
            p.obj[7].mundoY = 17 * p.tamBloco;

            p.obj[8] = new OBJ_Monstros();
            p.obj[8].mundoX = 12 * p.tamBloco;
            p.obj[8].mundoY = 25 * p.tamBloco;

            p.obj[9] = new OBJ_Monstros();
            p.obj[9].mundoX = 52 * p.tamBloco;
            p.obj[9].mundoY = 15 * p.tamBloco;

            p.obj[10] = new OBJ_Monstros();
            p.obj[10].mundoX = 28 * p.tamBloco;
            p.obj[10].mundoY = 20 * p.tamBloco;

            p.obj[11] = new OBJ_Monstros();
            p.obj[11].mundoX = 32 * p.tamBloco;
            p.obj[11].mundoY = 23 * p.tamBloco;

            p.obj[12] = new OBJ_Chefe();
            p.obj[12].mundoX = 49 * p.tamBloco;
            p.obj[12].mundoY = 19 * p.tamBloco;
        }
}
