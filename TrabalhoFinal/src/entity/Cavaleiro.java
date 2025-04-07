package entity;

public class Cavaleiro {
    public int vida   = 100;
    public int ataque = 60;
    public int defesa = 45;
    public Cavaleiro(int vida,int ataque,int defesa){
        this.vida += vida;
        this.ataque += ataque;
        this.defesa += defesa;
    }
}
