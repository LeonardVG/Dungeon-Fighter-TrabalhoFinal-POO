package objetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Monstros extends SuperObjetos {

    public final int dano = 35;
    public int vidaMonstro = 60;
    public final int vidaRestart =60;
    public OBJ_Monstros(){

        nome = "monstros";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/monstro F2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        colisao = true;
    }
}
