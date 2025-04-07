package objetos;



import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chefe extends SuperObjetos {

    public final int dano = 85;
    public int vidaChefe = 160;

    public OBJ_Chefe(){

        nome = "chefe";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objetos/chefe F2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        colisao = true;
    }
}
