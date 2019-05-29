package mapa;

import java.util.ArrayList;
import java.util.List;

public class No {

    private int id;
    float h, g, f;
    private boolean visitado, bloqueado, construivel;
    public List<No> vizinhos = new ArrayList();
    private No pai;
    
    public No(int id) {
        this.id = id;
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
    
    public void setConstruivel(boolean construivel){
        this.construivel = construivel;
    }
    public boolean isConstruivel() {
        return construivel;
    }

}
