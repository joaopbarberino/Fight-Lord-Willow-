package fight.lord.willow;

public class Base {

    /*
        Essa classe é responsável pela base do jogador
    
     */
    private int vida_maxima;
    private int vida_atual;
    private int xp;
    private int gold;

    public Base() {
        this.vida_maxima = 50;
        this.vida_atual = 50;
        this.xp = 0;
        this.gold = 0;

    }

    public int getVidaMaxima() {
        return vida_maxima;
    }

    private void setVidaMaxima(int vida_maxima) {
        this.vida_maxima = vida_maxima;
    }

    public int getVidaAtual() {
        return vida_atual;
    }

    public void setVidaAtual(int vida_atual) {
        this.vida_atual = vida_atual;
    }

    public int getXp() {
        return xp;
    }

    public void ganhaXp(int xp) {
        this.xp += xp;
    }
    
    public void ganhaGold(int valor) {
        this.gold += valor;        
    }
    
    public void recebeDano(int dano) {
        this.vida_atual = this.vida_atual - dano;
    }

    public void upgrade() {
        switch (this.xp) {
            case 50:
                this.vida_maxima += 20;
                this.vida_atual = (this.vida_maxima / 3);
                break;
            
            case 110:
                this.vida_maxima += 40;
                this.vida_atual = (this.vida_maxima / 3);
                break;
            
            case 160:
                this.vida_maxima += 60;
                this.vida_atual = (this.vida_maxima / 3);
                break;
            
            default:
                break;               
        }
    }

}
