package objetos;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Bomba extends SuperObjetos{

public final int dano = 25;

        public OBJ_Bomba(){

            nome = "bomba";

            try{
                image = ImageIO.read(getClass().getResourceAsStream("/objetos/bomba.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
            colisao = true;
        }


}
