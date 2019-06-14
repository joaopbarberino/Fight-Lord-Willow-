//***********************************************************/
//********************Referencias****************************/
//***************Professor Dr. Adalberto Bosco***************/
//https://www.linkedin.com/in/adalberto-pereira-08497517 ****/
//***********************************************************/
package mapa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AEstrela {

    public static List<No> lista_fechada = new ArrayList();
    public static List<No> lista_aberta = new ArrayList();
    public static List<No> caminho = new ArrayList();
    public static int colunas_do_mapa = 0;
    public static int linhas_do_mapa = 0;
    public static int tamanho_do_mapa = 0;
    public static ArrayList<Integer> caminhos = new ArrayList();

    public static List<No> aEstrela(No noInicial, No noDestino, Mapa mapa) {
        colunas_do_mapa = Mapa.getColunas();
        linhas_do_mapa = Mapa.getLinhas();
        tamanho_do_mapa = Mapa.getMapa().size();

        lista_fechada.clear();
        lista_aberta.clear();
        caminho.clear();

        boolean achouCaminho = false;

        No noAtual = noInicial;
        lista_aberta.add(noInicial);

        while (!achouCaminho) {
            noAtual = procurarMenorF();
            lista_aberta.remove(noAtual);
            lista_fechada.add(noAtual);
            achouCaminho = noAtual.equals(noDestino);
            for (No no : noAtual.getVizinhos()) {
                if (no.isBloqueado() || lista_fechada.contains(no) || no.isConstruivel()) {
                    continue;
                } else {
                    if (!lista_aberta.contains(no)) {
                        lista_aberta.add(no);
                        no.setPai(noAtual);
                        no.setH(calcularH(no, noDestino));
                        no.setG(calcularG(no, noAtual));
                        no.setF(calcularF(no));
                    } else {
                        if (no.getG() < noAtual.getG()) {
                            no.setPai(noAtual);
                            no.setG(calcularG(noAtual, no));
                            no.setF(calcularF(no));
                        }
                    }
                }
            }
            if (lista_fechada.isEmpty()) {
                System.out.println("Nao achou caminho");
                return null;
            }
        }
        return montaCaminho(noInicial, noDestino, mapa);
    }

    private static No procurarMenorF() {
        Collections.sort(lista_aberta, Comparator.comparing(No::getF));
        return lista_aberta.get(0);
    }

    private static float calcularH(No noAtual, No noDestino) {
        int posicaoDestinoX = (noDestino.getId() % colunas_do_mapa) + 1;
        int posicaoNoAtualX = (noAtual.getId() % colunas_do_mapa) + 1;
        int distanciaX = posicaoDestinoX > posicaoNoAtualX ? posicaoDestinoX - posicaoNoAtualX : posicaoNoAtualX - posicaoDestinoX;

        int posicaoDestinoY = (noDestino.getId() / linhas_do_mapa) + 1;
        int posicaoNoAtualY = (noAtual.getId() % linhas_do_mapa) + 1;
        int distanciaY = posicaoDestinoY > posicaoNoAtualY ? posicaoDestinoY - posicaoNoAtualY : posicaoNoAtualY - posicaoDestinoY;

        float distanciaTotal = (float) Math.sqrt((Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2))) * 10;
        return distanciaTotal;
    }

    private static float calcularG(No noAtual, No noVizinho) {
        if (noVizinho.getId() % colunas_do_mapa == noAtual.getId() % colunas_do_mapa || noVizinho.getId() + 1 == noAtual.getId() || noVizinho.getId() - 1 == noAtual.getId()) {
            return noVizinho.getG() + 10;
        }
        return noVizinho.getG() + 14;
    }

    private static float calcularF(No no) {
        return no.getG() + no.getH();
    }

    private static List<No> montaCaminho(No noInicial, No noDestino, Mapa mapa) {
        List<No> listaAuxiliar = new ArrayList();
        No noAtual = noDestino;
        int contador = 0;
        while (!listaAuxiliar.contains(noInicial) || contador > tamanho_do_mapa) {
            listaAuxiliar.add(noAtual);

            noAtual = noAtual.getPai();

            contador++;
        }
        
        Collections.reverse(listaAuxiliar);

        //imprimir caminho
        System.out.println("Caminho: ");
        for (No no : listaAuxiliar) {
            caminhos.add(no.getId());
        }
        //inicio artificio apenas para printar caminho
        for (No no : mapa.getMapa()) {
            if (!listaAuxiliar.contains(no)) {
                no.setPai(null);
            }

        }
        //fim do artificio

        desenha(mapa);

        //retorno do caminho
        return listaAuxiliar;
    }

    public static void desenha(Mapa mapa) {
        System.out.println("");
        for (int i = 0; i < mapa.getLinhas(); i++) {
            for (int j = 0; j < mapa.getColunas(); j++) {
                No no = mapa.getMapa().get((i * mapa.getColunas()) + j);
                if (no.getPai() != null) {
                    System.out.print("[-]");
                } else if (no.isBloqueado()) {
                    System.out.print("[X]");
                } else if (no.isConstruivel()) {
                    System.out.print("[/]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }
}
