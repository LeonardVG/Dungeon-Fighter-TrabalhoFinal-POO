package objetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_pocao extends SuperObjetos{
 public final int plusVida = 25;
    public OBJ_pocao(){

        nome = "pocao";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/pocao.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        colisao = true;
    }
}
