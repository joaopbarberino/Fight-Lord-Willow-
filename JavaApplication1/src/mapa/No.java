//***********************************************************/
//********************Referencias****************************/
//***************Professor Dr. Adalberto Bosco***************/
//https://www.linkedin.com/in/adalberto-pereira-08497517 ****/
//***********************************************************/

package mapa;

import java.util.ArrayList;
import java.util.List;
import javaapplication1.Engine;

public class No {

    private int id, pos[],qtd_colunas,x,y;
    float h, g, f;
    private boolean visitado, bloqueado, construivel;
    public List<No> vizinhos = new ArrayList();
    private No pai;

    public No(int id) {
        this.id = id;
        this.pos = new int[2];
        this.qtd_colunas = Mapa.getColunas();
        this.x = ((this.getId() % qtd_colunas) * Engine.TILE_SIZE);
        this.y = ((this.getId() / qtd_colunas) * Engine.TILE_SIZE);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setPos() {
        this.pos[0] = this.x;
        this.pos[1] = this.y;
    }

    public int[] getPos() {
        return this.pos;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public List<No> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(List<No> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public void setConstruivel(boolean construivel) {
        this.construivel = construivel;
    }

    public boolean isConstruivel() {
        return construivel;
    }

    @Override
    public String toString() {
        return "{" +this.id +'}';
    }

}
