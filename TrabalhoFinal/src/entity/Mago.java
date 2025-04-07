package entity;

public class Mago {
    public int vida   = 90;
    public int ataque = 70;
    public int defesa = 25;
    public Mago(int vida,int ataque,int defesa){
        this.vida += vida;
        this.ataque += ataque;
        this.defesa += defesa;
    }
}
