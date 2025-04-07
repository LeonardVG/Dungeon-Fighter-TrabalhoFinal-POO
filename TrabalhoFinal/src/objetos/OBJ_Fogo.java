package objetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Fogo extends SuperObjetos{

    public final int dano = 65;

    public OBJ_Fogo(){

        nome = "fogo";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/fogo 2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        colisao = true;
    }
}
