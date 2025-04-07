package entity;

public class Arqueiro {
    public int vida   = 100;
    public int ataque = 30;
    public int defesa = 50;

    public Arqueiro(int vida,int ataque,int defesa){
        this.vida += vida;
        this.ataque += ataque;
        this.defesa += defesa;
    }
    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }

    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
}
